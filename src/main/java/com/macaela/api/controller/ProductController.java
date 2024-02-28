package com.macaela.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.macaela.api.models.product.DatosObtenerProductos;
import com.macaela.api.models.product.DatosRegistroProducto;
import com.macaela.api.models.product.Product;
import com.macaela.api.models.user.User;
import com.macaela.api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
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
            // Aquí puedes realizar la lógica para mapear el DTO a una entidad Product y
            // guardarla en la base de datos
            Product product = new Product();
            product.setCategory(datosRegistroProducto.getCategory());
            product.setColor(datosRegistroProducto.getColor());
            product.setSize(datosRegistroProducto.getSize());
            product.setStock(datosRegistroProducto.getStock());
            product.setPrice(datosRegistroProducto.getPrice());
            product.setDescription(datosRegistroProducto.getDescription());
            product.setImage(datosRegistroProducto.getImage());
            // Aquí estableces la relación con el usuario
            // Supongamos que tienes el ID del usuario en una variable userId
            User user = new User();
            user.setUserId(datosRegistroProducto.getUserId()); // Estableces el ID del usuario
            product.setUserId(user); // Estableces la relación

            productRepository.save(product); // Guardas el producto en la base de datos

            return ResponseEntity.status(HttpStatus.CREATED).body(datosRegistroProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el producto");
        }
    }
}
