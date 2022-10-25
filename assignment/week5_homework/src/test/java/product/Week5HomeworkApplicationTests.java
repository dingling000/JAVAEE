package product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import product.entity.Product;
import product.service.ProductService;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
class Week5HomeworkApplicationTests {


	@Autowired
	ProductService productService;

	@Test
	void addProductTest() {
		Product product = new Product();
		product.setName("iphone");
		product.setPrice(8000);
		productService.addProduct(product);
	}

	@Test
	void getProductByIdTest(){
		System.out.println(productService.getProductById(5));
	}

	@Test
	void getProductByNameTest(){
		System.out.println(productService.getProductByName("iphone14pro"));
	}


}
