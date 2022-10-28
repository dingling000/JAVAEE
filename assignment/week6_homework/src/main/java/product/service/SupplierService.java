package product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.Dao.SupplierMybatisPlusMapper;
import product.domain.ProductDto;
import product.domain.Supplier;
import product.exception.ProductAdminException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SupplierService {
    @Autowired
    SupplierMybatisPlusMapper supplierMybatisPlusMapper;
    //添加供应商
    public Supplier addSupplier(Supplier supplier) throws ProductAdminException {
        if (supplierMybatisPlusMapper.selectById(supplier.getId()) != null) {
            throw new ProductAdminException("添加供应商失败：供应商已经存在");
        }
        supplierMybatisPlusMapper.insert(supplier);
        return supplier;
    }

    public void updateSupplier(long id, Supplier supplier) throws ProductAdminException {
        if (supplierMybatisPlusMapper.selectById(id) == null ) {
            throw new ProductAdminException("修改供应商失败：供应商"+id+"不存在");
        }
        supplierMybatisPlusMapper.updateById(supplier);
    }


    public Supplier getSupplier(long id){
        return supplierMybatisPlusMapper.selectById(id);
    }

    public void deleteSupplier(long id) {
        supplierMybatisPlusMapper.deleteById(id);
    }


}
