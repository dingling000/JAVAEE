package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.domain.ProductDto;
import product.domain.Supplier;
import product.service.ProductDtoService;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@RestController
@RequestMapping("productsupplier")
public class ProductDtoController {
    @Autowired
    ProductDtoService productDtoService;

    @GetMapping("/find/{id}")
    public List<ProductDto> findProductSupplier(@PathVariable long id){
        return productDtoService.findProductSupplier(id);
    }

    @GetMapping("/{id}")
    public List<Supplier> findSupplierByPro(@PathVariable long id){
        return productDtoService.findSupplierByPro(id);
    }

}
