package com.macaela.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.macaela.api.models.product.DatosObtenerProductos;
import com.macaela.api.models.product.DatosRegistroProducto;
import com.macaela.api.models.product.Product;
import com.macaela.api.models.user.User;
import com.macaela.api.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody DatosRegistroProducto datosRegistroProducto) {
        try {
            Product product = new Product();
            product.setCategory(datosRegistroProducto.getCategory());
            product.setColor(datosRegistroProducto.getColor());
            product.setSize(datosRegistroProducto.getSize());
            product.setStock(datosRegistroProducto.getStock());
            product.setPrice(datosRegistroProducto.getPrice());
            product.setDescription(datosRegistroProducto.getDescription());
            product.setImage(datosRegistroProducto.getImage());
            
            User user = new User();
            user.setUserId(datosRegistroProducto.getUserId());
            product.setUserId(user);

            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(datosRegistroProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el producto");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosObtenerProductos> getProductById(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id)
                .orElse(null);
        if (product != null) {
            DatosObtenerProductos productDTO = mapProduct(product);
            return ResponseEntity.ok(productDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    

    // Endpoint para obtener todos los productos
    @GetMapping("/all")
    public ResponseEntity<List<DatosObtenerProductos>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<DatosObtenerProductos> productDTOs = products.stream()
                .map(this::mapProduct)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();

                // Obtener el usuario asociado al producto
                User user = product.getUserId();

                // Si el producto está asociado a un usuario
                if (user != null) {
                    // Eliminar el producto de la lista de productos del usuario
                    user.getProducts().remove(product);
                }

                // Eliminar el producto
                productRepository.deleteById(id);

                return ResponseEntity.ok("Producto eliminado correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto");
        }
    }

    // Endpoint para obtener todos los productos asociados con un usuario específico
    @GetMapping("/users/{userId}")
    public ResponseEntity<List< DatosObtenerProductos>> getProductsByUserId(@PathVariable("userId") Long userId) {
    	  List<Product> products = productRepository.findByUserId_Id(userId);
        
        if (products != null && !products.isEmpty()) {
            
            List<DatosObtenerProductos> productDTOs = products.stream()
                    .map(this::mapProduct)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productDTOs);
        } else {
            return ResponseEntity.notFound().build();
            }
        }
    
//    // Método para obtener productos por tamaño
//    @GetMapping("/bySize")
//    public ResponseEntity<List<DatosObtenerProductos>> getProductsBySize(@RequestParam("size") String size) {
//        List<Product> products = productRepository.findBySize(size);
//        if (products != null && !products.isEmpty()) {
//            List<DatosObtenerProductos> productDTOs = products.stream()
//                    .map(this::mapProduct)
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok(productDTOs);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Método para obtener productos por categoría
//    @GetMapping("/byCategory")
//    public ResponseEntity<List<DatosObtenerProductos>> getProductsByCategory(@RequestParam("category") String category) {
//        List<Product> products = productRepository.findByCategory(category);
//        if (products != null && !products.isEmpty()) {
//            List<DatosObtenerProductos> productDTOs = products.stream()
//                    .map(this::mapProduct)
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok(productDTOs);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Método para obtener productos por precio menor que el valor dado
//    @GetMapping("/byPriceLessThan")
//    public ResponseEntity<List<DatosObtenerProductos>> getProductsByPriceLessThan(@RequestParam("price") BigDecimal price) {
//        List<Product> products = productRepository.findByPriceLessThan(price);
//        if (products != null && !products.isEmpty()) {
//            List<DatosObtenerProductos> productDTOs = products.stream()
//                    .map(this::mapProduct)
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok(productDTOs);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Método para obtener productos por precio mayor que el valor dado
//    @GetMapping("/byPriceGreaterThan")
//    public ResponseEntity<List<DatosObtenerProductos>> getProductsByPriceGreaterThan(@RequestParam("price") BigDecimal price) {
//        List<Product> products = productRepository.findByPriceGreaterThan(price);
//        if (products != null && !products.isEmpty()) {
//            List<DatosObtenerProductos> productDTOs = products.stream()
//                    .map(this::mapProduct)
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok(productDTOs);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    private DatosObtenerProductos mapProduct(Product product) {
        DatosObtenerProductos productDTO = new DatosObtenerProductos();
        productDTO.setId(product.getId());
        productDTO.setCategory(product.getCategory());
        productDTO.setColor(product.getColor());
        productDTO.setSize(product.getSize());
        productDTO.setStock(product.getStock());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        return productDTO;
    }
}
