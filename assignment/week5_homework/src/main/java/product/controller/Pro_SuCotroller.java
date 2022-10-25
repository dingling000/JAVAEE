package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.entity.Product;
import product.service.Pro_SuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product_supplier")
public class Pro_SuCotroller {
    @Autowired
    Pro_SuService pro_suService;

    @PostMapping("/add")
    public Map<String,Object> addProduct(@RequestBody  Map<String, String> m){
        Map<String,Object> map = new HashMap<>();
        String proname=m.get("proname");
        String sname=m.get("sname");
        try {
            String result = pro_suService.addConnection(proname,sname);
            map.put("success",true);
            map.put("message",result);
            //return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","关联新增失败");
            //return null;
        }
        return map;
    }
}
