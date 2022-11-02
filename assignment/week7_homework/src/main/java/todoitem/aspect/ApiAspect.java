package todoitem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
public class ApiAspect {
    final Map<String,Long> metricsTimes= Collections.synchronizedMap(new HashMap<>());

    //获得执行次数
    @Around("within(todoitem.service.TodoService)")
    public void calculateTimes(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodSig=joinPoint.getSignature().toString();
        long times= metricsTimes.containsKey(methodSig)?metricsTimes.get(methodSig):0;
        joinPoint.proceed();
        times++;
        metricsTimes.put(methodSig,times);
    }

    public Map<String, Long> getMetricsTimes() {
        return metricsTimes;
    }

    //获得响应时间
    final Map<String,Long> metricsTime= Collections.synchronizedMap(new HashMap<>());

    @Around("within(todoitem.service.TodoService)")
    public void calculateTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long t1= Calendar.getInstance().getTimeInMillis();
        joinPoint.proceed();
        long t2= Calendar.getInstance().getTimeInMillis();
        String methodSig=joinPoint.getSignature().toString();
        long base= metricsTime.containsKey(methodSig)?metricsTime.get(methodSig):0;
        metricsTime.put(methodSig,base+t2-t1);
    }

    public void getMetricsTime() {
        long max=0;
        String maxStr="";
        long min=Integer.MAX_VALUE;
        String minStr="";
        long sum=0;
        for(String key:metricsTime.keySet()){
            Long val=metricsTime.get(key);
            if(val>max){
                max=val;
                maxStr=key;
            }
            if(val<min){
                min=val;
                minStr=key;
            }
            sum+=val;
        }
        System.out.println(maxStr+"响应时间最长，最长响应时间为"+max);
        System.out.println(minStr+"响应时间最短，最短响应时间为"+min);
        System.out.println("平均响应时间为"+(sum/metricsTime.size()));
    }

    //获得异常次数

    long express=0;
    @AfterThrowing(pointcut= "within(todoitem.service.TodoService)",throwing="ex")
    public void  afterPointCutThrow(JoinPoint jp, Exception ex) {
        express++;
        System.out.println("当前发生异常的次数为"+express);
    }


}
