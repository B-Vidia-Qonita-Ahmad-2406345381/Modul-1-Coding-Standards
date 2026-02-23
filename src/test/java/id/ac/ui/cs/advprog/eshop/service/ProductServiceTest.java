package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    ProductService productService = new ProductServiceImpl();
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-72af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        Product productFound = productService.findById("eb558e9f-1c39-460e-8860-72af6af63bd6");
        assertNotNull(productFound, "product should not be null");
        assertEquals(product.getProductId(), productFound.getProductId());
        assertEquals(product.getProductName(), productFound.getProductName());
        assertEquals(product.getProductQuantity(), productFound.getProductQuantity());
    }
    @Test
    void testCreateAndGenerateId() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        Product savedProduct = productList.get(0);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindByIdListEmpty() {
        Product productFound = productService.findById("eb558e9f-1c39-460e-8860-72af6af63bd6");
        assertNull(productFound, "product should be null");
        List<Product> productList = productService.findAll();
        assertTrue(productList.isEmpty());
    }
    @Test
    void testFindByIdListNotEmpty() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        Product productFound = productService.findById("eb558e9f-1c39-460e-8860-72af6af63bd6");
        assertNull(productFound, "product should be null");
    }
    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Pensil");
        product.setProductQuantity(5);
        productService.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Pulpen");
        updatedProduct.setProductQuantity(10);

        Product result = productService.edit(updatedProduct);

        assertNotNull(result);
        assertEquals("Pulpen", result.getProductName());
        assertEquals(10, result.getProductQuantity());
    }
    @Test
    void testDeleteFirstProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Buku");
        product.setProductQuantity(20);
        productService.create(product);

        boolean isDeleted = productService.delete("1");

        assertTrue(isDeleted);

        List<Product> productList = productService.findAll();
        assertTrue(productList.isEmpty());
    }
    @Test
    void testDeleteProductNotFound() {
        boolean isDeleted = productService.delete("999");
        assertFalse(isDeleted);
    }
    @Test
    void testClearProductData() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Buku");
        product.setProductQuantity(20);
        productService.create(product);

        productService.clear();

        List<Product> productList = productService.findAll();
        assertTrue(productList.isEmpty());
    }
}
