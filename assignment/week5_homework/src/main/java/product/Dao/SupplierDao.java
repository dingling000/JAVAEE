package product.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import product.entity.Supplier;

public interface SupplierDao extends JpaRepository<Supplier,Long>, JpaSpecificationExecutor<Supplier> {
}
