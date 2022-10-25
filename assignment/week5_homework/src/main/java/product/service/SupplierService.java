package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import product.Dao.SupplierDao;
import product.entity.Product;
import product.entity.Supplier;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierDao supplierDao;

    public Supplier getSupplierById(long id){
        return supplierDao.getReferenceById(id);
    }

    public Supplier addSupplier(Supplier s){
        return supplierDao.saveAndFlush(s);
    }

    public void updateSupplier(long id,Supplier s){
        supplierDao.save(s);
    }

    public void deleteProduct(long id){
        supplierDao.deleteById(id);
    }

    public List<Supplier> getSupplierByName(String name){
        Specification<Supplier> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (name!="") {
                predicateList.add(criteriaBuilder.equal(root.get("supplier"),name));
            }

            Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
            return criteriaBuilder.and(predicates);
        };

        List<Supplier> result = supplierDao.findAll(specification);
        return result;
    }
}
