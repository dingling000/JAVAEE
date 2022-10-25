package product.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import product.entity.Product;

public interface ProductDao extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

}
