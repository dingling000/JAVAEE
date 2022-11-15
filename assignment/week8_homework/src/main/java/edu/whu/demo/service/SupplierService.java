package edu.whu.demo.service;

import edu.whu.demo.dao.SupplierRepository;
import edu.whu.demo.entity.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Supplier addSupplier(Supplier supplier) throws ProductAdminException {
        if (supplier.getId() != null) {
            if (supplierRepository.findById(supplier.getId()).isPresent()) {
                throw new ProductAdminException("添加供应商失败：供应商已经存在");
            }
        }
        return supplierRepository.saveAndFlush(supplier);
    }

    public Supplier updateSupplier(long id, Supplier supplier) throws ProductAdminException {
        if (!supplierRepository.findById(id).isPresent()) {
            throw new ProductAdminException("修改供应商失败：供应商"+id+"不存在");
        }
        return supplierRepository.saveAndFlush(supplier);
    }

    public Page<Supplier> findSuppliers(String name, Pageable pageable){
        return supplierRepository.findByNameContaining(name,pageable);
    }

    public Supplier getSupplier(long id){
        return supplierRepository.findById(id).get();
    }

    public void deleteSupplier(long id) {
        supplierRepository.deleteById(id);
    }
}
