package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.Dao.ProductDao;
import product.entity.Product;
import product.entity.Supplier;

import java.util.List;

@Service
public class Pro_SuService {

    @Autowired
    ProductService productService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    ProductDao productDao;
    public String addConnection(String proName, String sName){
        List<Product> productList=productService.getProductByName(proName);
        //System.out.println(productList);
        List<Supplier> supplierList=supplierService.getSupplierByName(sName);
        //System.out.println(supplierList);
        if(productList.size()>0){
            productList.get(0).setSuppliers(supplierList);
            Product pIm= productDao.save(productList.get(0));
        }

        return "添加关联成功";
    }

}
