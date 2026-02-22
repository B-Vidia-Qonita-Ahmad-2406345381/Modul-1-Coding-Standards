package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-72af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-72af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Pensil");
        product.setProductQuantity(5);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Pulpen");
        updatedProduct.setProductQuantity(10);

        Product result = productRepository.update(updatedProduct);

        assertNotNull(result);
        assertEquals("Pulpen", result.getProductName());
        assertEquals(10, result.getProductQuantity());

        Iterator<Product> productIterator = productRepository.findAll();
        Product savedProduct = productIterator.next();

        assertEquals("Pulpen", savedProduct.getProductName());
        assertEquals(10, savedProduct.getProductQuantity());
    }
    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("2");
        product.setProductName("Buku");
        product.setProductQuantity(20);
        productRepository.create(product);

        boolean isDeleted = productRepository.delete("2");

        assertTrue(isDeleted);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testUpdateProductNotFound() {
        Product product = new Product();
        product.setProductId("999");
        product.setProductName("Ghost");
        product.setProductQuantity(0);

        Product result = productRepository.update(product);
        assertNull(result);
    }
    @Test
    void testDeleteProductNotFound() {
        boolean isDeleted = productRepository.delete("999");
        assertFalse(isDeleted);
    }
}
