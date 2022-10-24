package product.week4_homework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import product.week4_homework.entity.Product;
import product.week4_homework.service.ProductService;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Week4HomeworkApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	ProductService productService;

	@Autowired
	private TestRestTemplate restTemplate;

	String url;

	@PostConstruct
	private void init(){
		this.url="http://localhost:" + port + "/product";
	}

	@AfterEach
	private void clean(){
		productService.deleteAll();
	}

	@BeforeEach
	private void initData(){
		Product product=new Product();
		product.setId(1);
		product.setName("IPhone 13");
		product.setPrice(8000);
		product.setProductType("SND-dff");
		product.setCategory("手机");
		product.setStockQuantity(20);
		productService.addProduct(product);
	}


	@Test
	public void testAddProduct(){
		Product product=new Product();
		product.setId(2);
		product.setName("IPhone 18");
		product.setPrice(12000);
		product.setProductType("SND-dff");
		product.setCategory("手机");
		product.setStockQuantity(10);
		ResponseEntity<Product> result = restTemplate.postForEntity(url, product, Product.class);
		assertEquals(HttpStatus.OK,result.getStatusCode());
		assertNotNull(result.getBody());

		Product product1=productService.getProduct(2);
		assertEquals(product.getName(),product1.getName());
		assertEquals(product.getPrice(),product1.getPrice());
	}

	@Test
	public void testFindProduct(){
		Product product=new Product();
		product.setId(2);
		product.setName("IPhone 18");
		product.setPrice(12000);
		product.setProductType("SND-dff");
		product.setCategory("手机");
		product.setStockQuantity(10);
		productService.addProduct(product);

		ResponseEntity<Product[]> result = restTemplate.getForEntity(url,Product[].class);
		assertEquals(HttpStatus.OK,result.getStatusCode());
		assertEquals(2,result.getBody().length);
		result = restTemplate.getForEntity(url+"?name={name}&quantity={quantity}",
				Product[].class,"IPhone",15);
		assertEquals(HttpStatus.OK,result.getStatusCode());
		assertEquals(1,result.getBody().length);
	}

	@Test
	public void testUpdateProduct(){
		Product product=  productService.getProduct(1);
		product.setStockQuantity(15);
		restTemplate.put(url+"/1", product, Product.class);
		product=  productService.getProduct(1);
		assertEquals(15,product.getStockQuantity());
	}

	@Test
	public void testDeleteProduct(){
		restTemplate.delete(url+"/1");
		assertNull(productService.getProduct(1));
	}


}
