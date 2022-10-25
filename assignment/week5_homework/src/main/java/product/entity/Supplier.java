package product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import sun.util.resources.ga.LocaleNames_ga;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String supplier;


}
