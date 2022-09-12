# 第一周笔记---学习Java语法

因为之前并没有系统学习Java语法，所以本周笔记大部分为自己在看书学习语法时的部分笔记，只记录java语法与C++相异的部分。

## 1. 数据类型

### 1.1 Java语言数据类型的分类

- 基本数据类型
- 引用数据类型

### 1.2 基本数据类型

| 数据类型 | 关键字  | 内存占用 |                 取值范围                  |
| :------: | :-----: | :------: | :---------------------------------------: |
|   整数   |  byte   |    1     |    负的2的7次方 ~ 2的7次方-1(-128~127)    |
|          |  short  |    2     | 负的2的15次方 ~ 2的15次方-1(-32768~32767) |
|          |   int   |    4     |        负的2的31次方 ~ 2的31次方-1        |
|          |  long   |    8     |        负的2的63次方 ~ 2的63次方-1        |
|  浮点数  |  float  |    4     |        1.401298e-45 ~ 3.402823e+38        |
|          | double  |    8     |      4.9000000e-324 ~ 1.797693e+308       |
|   字符   |  char   |    2     |                  0-65535                  |
|   布尔   | boolean |    1     |                true，false                |

在java中整数默认是int类型，浮点数默认是double类型。

- 如果要定义一个long类型的变量，那么在数据值的后面需要加上L后缀。
- 如果要定义一个float类型的变量，那么在数据值的后面需要加上F后缀。

整数类型和小数类型的取值范围大小关系：

​	double > float > long > int > short > byte

## 2. 键盘录入

​	键盘录入的实际功能放在了Scanner这个类中。

使用步骤：

第一步：导包：导入Scanner类。

第二步：创建对象。

第三步：接收数据。

```java
import java.util.Scanner;
public class ScannerDemo1{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个数字");
		int i = sc.nextInt();
		System.out.println(i);
	}
}
```

## 3.类型转换

### 3.1隐式转换

​	也叫自动类型提升。

​	就是把一个取值范围小的数据或者变量，赋值给另一个取值范围大的变量。此时不需要我们额外写代码单独实现，是程序自动帮我们完成的。

#### 两种提升规则：

* 取值范围小的，和取值范围大的进行运算，小的会先提升为大的，再进行运算。
* byte、short、char三种类型的数据在运算的时候，都会直接先提升为int，然后再进行运算。

#### 取值范围从小到大的关系：

​	byte  short  int  long  float  double

### 3.2强制转换

如果要把一个取值范围大的数据或者变量赋值给另一个取值范围小的变量。是不允许直接操作。则需要进行强制类型转换。

强制类型转换格式：

​	目标数据类型 变量名 = （目标数据类型）被强转的数据；

## 5.流程控制语句

### switch在JDK12的新特性

```java
int number = 10;
switch (number) {
    case 1 -> System.out.println("一");
    case 2 -> System.out.println("二");
    case 3 -> System.out.println("三");
    default -> System.out.println("其他");
}
```

其他流程控制与C++的类似

## 6.数组

静态初始化  

```java
int[] array={,,};
```

动态初始化

```java
int[] arr=new int[50];
```

## 7.方法

与C++中的函数类似

### 7.1 方法重载

* 方法重载概念

  方法重载指同一个类中定义的多个方法之间的关系，满足下列条件的多个方法相互构成重载

  * 多个方法在同一个类中

  * 多个方法具有相同的方法名

  * 多个方法的参数不相同，类型不同或者数量不同

    

方法调用会进入栈中保存，调用完毕后会将方法移出栈

## 8.引用数据类型&基本数据类型对比

基本数据类型在栈中直接存储数据，赋值时也是赋真实的值

引用数据类型会在方法栈里面存储数据在堆中的地址，赋值时赋地址，那么将指向同一块地址空间

## 9.面向对象

### 9.1 类的定义

类的组成是由属性和行为两部分组成

* 属性：在类中通过成员变量来体现（类中方法外的变量）
* 行为：在类中通过成员方法来体现（和前面的方法相比去掉static关键字即可）

类的定义步骤：

①定义类

②编写类的成员变量

③编写类的成员方法

```java
/*
    手机类：
        类名：
        手机(Phone)

        成员变量：
        品牌(brand)
        价格(price)

        成员方法：
        打电话(call)
        发短信(sendMessage)
 */
public class Phone {
    //成员变量
    String brand;
    int price;

    //成员方法
    public void call() {
        System.out.println("打电话");
    }

    public void sendMessage() {
        System.out.println("发短信");
    }
}
```

### 9.2对象的使用

* 创建对象的格式：
  * 类名 对象名 = new 类名();
* 调用成员的格式：
  * 对象名.成员变量
  * 对象名.成员方法();

### 9.3 JavaBean类

不含main方法，只用来描述事物

标准JavaBean应该包含成员变量（最好用private），包括构造方法（无参和有参），包括对于每一个成员变量的get，set方法，还有其他所需的方法。

快捷键alt+insert

插件ptg

### 9.4封装

在实际运用中，面对复杂需求需要进行优化封装。

对象代表什么，就得封装对应的数据，并提供数据对应的行为。例如：人关门，涉及到人的类，门的类，其中关门的方法应该在门类中。

### 9.5 private

只有在本类中才能使用

被private修饰的成员，只能在本类进行访问，针对private修饰的成员变量，如果需要被其他类使用，提供相应的操作

* 提供“get变量名()”方法，用于获取成员变量的值，方法用public修饰
* 提供“set变量名(参数)”方法，用于设置成员变量的值，方法用public修饰

```java

class Student {
    //成员变量
    String name;
    private int age;

    //提供get/set方法
    public void setAge(int a) {
        if(a<0 || a>120) {
            System.out.println("你给的年龄有误");
        } else {
            age = a;
        }
    }

    public int getAge() {
        return age;
    }

    //成员方法
    public void show() {
        System.out.println(name + "," + age);
    }
}
/*
    学生测试类
 */
public class StudentDemo {
    public static void main(String[] args) {
        Student s = new Student();
        s.name = "丁玲";
        s.setAge(30);
        s.show();
    }
}
```

###  9.6构造方法

构造方法是一种特殊的方法

* 作用：创建对象   Student stu = **new Student();**

* 格式：

  public class 类名{

  ​        修饰符 类名( 参数 ) {

  ​        }

  }

* 功能：主要是完成对象数据的初始化

  

```java
class Student {
    private String name;
    private int age;

    //构造方法
    public Student() {
        System.out.println("无参构造方法");
    }

    public void show() {
        System.out.println(name + "," + age);
    }
}
/*
    测试类
 */
public class StudentDemo {
    public static void main(String[] args) {
        //创建对象
        Student s = new Student();
        s.show();
    }
}
```

## 10.Java内存空间

在jvm中Java内存分配为：本地方法栈，寄存器，栈，堆，方法区（字节码文件加载进入的内存）。方法调用进入的内存是栈，引用变量会存在堆中。

## 11.字符串

### 11.1 String

String 类在 java.lang 包下，所以使用的时候不需要导包！

字符串不可变，它们的值在创建后不能被更改

虽然 String 的值是不可变的，但是它们可以被共享

字符串效果上相当于字符数组( char[] )，但是底层原理是字节数组( byte[] )

### 11.2 字符串的比较 == & equals

#### 11.2.1==号的作用

- 比较基本数据类型：比较的是具体的值
- 比较引用数据类型：比较的是对象地址值

#### 11.2.2equals方法的作用

```java
public boolean equals(String s)     比较两个字符串内容是否相同、区分大小写
```

### 11.2 StringBuilder

StringBuilder 可以看成是一个容器，创建之后里面的内容是可变的。

当我们在拼接字符串和反转字符串的时候会使用到

使用：

```java
public class StringBuilderDemo3 {
    public static void main(String[] args) {
        //1.创建对象
        StringBuilder sb = new StringBuilder("abc");

        //2.添加元素
        /*sb.append(1);
        sb.append(2.3);
        sb.append(true);*/

        //反转
        sb.reverse();

        //获取长度
        int len = sb.length();
        System.out.println(len);


        //打印
        //普及：
        //因为StringBuilder是Java已经写好的类
        //java在底层对他做了一些特殊处理。
        //打印对象不是地址值而是属性值。
        System.out.println(sb);
    }
}
```

## 12 Java与C++的对比

在这几日的Java学习中，感觉Java面向对象的思想更浓厚一些，在Java中有很多封装好的类，体现了面向对象的调用的思想。同时Java运行在Java虚拟机之上，可以进行跨平台开发。个人认为java在对待实际功能需求的开发中会更加实用，同时Java也要求我们学会类的思想，需要我们学会抽象。