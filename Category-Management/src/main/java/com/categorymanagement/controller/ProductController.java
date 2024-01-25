package com.categorymanagement.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.categorymanagement.entity.Category;
import com.categorymanagement.entity.Products;
import com.categorymanagement.repository.ProductRepository;
import com.categorymanagement.service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	//for geting all products
	@GetMapping("/getAll")
	public ResponseEntity<List<Products>> getAllProducts() {
		List<Products> productsList = productService.getAllProducts();
		return new ResponseEntity<List<Products>>(productsList, HttpStatus.OK);
	}

	//for getting product by id
	@GetMapping("getById/{id}")
	public ResponseEntity<Products> getProductById(@PathVariable long id) {
		Products product = productService.getProductById(id);
		return new ResponseEntity<Products>(product, HttpStatus.FOUND);
	}

	//for adding product
	@PostMapping("/add")
	public ResponseEntity<Products> addProduct(@Valid @RequestBody Products model) {
		Products newProduct = productService.addProduct(model);
		return new ResponseEntity<Products>(newProduct, HttpStatus.CREATED);
	}

	//for deleteing product
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//for uploading/setting product image
	@PostMapping("/upload/{pId}")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Long pId)
			throws IOException {
		String uploadImage = productService.uploadImage(file, pId);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

//	@PostMapping("/update/{productId}")
//	public ResponseEntity<Products> updateProduct(@PathVariable Long productId, @Valid @RequestBody Products updatePro) {
//		
//		Products existPro = productService.getProductById(productId);
//
//        existPro.setTitle(updatePro.getTitle());
//        existPro.setDescription(updatePro.getDescription());
//        existPro.setQuantity(updatePro.getQuantity());
//        existPro.setPrice(updatePro.getPrice());
//        existPro.setBrand(updatePro.getBrand());
//        existPro.setSku(updatePro.getSku());
//        existPro.setCategories(updatePro.getCategories());
//
//        Products updatedProductEntity = productService.addProduct(existPro);
//        return ResponseEntity.ok(updatedProductEntity);
//    }

	//for updating product
	@PostMapping("/update/{productId}")
	public ResponseEntity<Products> updateProduct(@PathVariable Long productId,
			@Valid @RequestBody Products updatePro) {
		Products updatedPro = productService.updateProduct(productId, updatePro);
		return new ResponseEntity<Products>(updatedPro, HttpStatus.OK);
	}

	//for downloading/fetching product image
	@GetMapping("/download/{pId}")
	public ResponseEntity<?> downloadImage(@PathVariable Long pId) {
		byte[] imageData = productService.downloadImage(pId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}

	//for saving multiple products
	@PostMapping("/saveMultiple")
	public ResponseEntity<List<Products>> saveMultipleProducts(@RequestBody List<Products> models) {
	    List<Products> allSavedPro = productService.saveMultipleProducts(models);
	    return new ResponseEntity<>(allSavedPro, HttpStatus.OK);
	}
	
	//for deleting all products
	@DeleteMapping("/deleteAll")
	public ResponseEntity<?> deleteAllPro() {
		productService.deleteAllPro();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//for searching product by using native query
	@GetMapping("/searchProduct/{proName}")
	public ResponseEntity<List<Products>> findProduct(@PathVariable String proName) throws Exception {
		List<Products> list = productService.findProduct(proName);
		return new ResponseEntity<List<Products>>(list, HttpStatus.OK);
	}
	
	//for finding products by there sku
	@GetMapping("/findBySKU/{sku}")
	public ResponseEntity<Products> findProductBySKU(@PathVariable String sku) throws Exception {
		Products list = productService.findProductBySKU(sku);
		return new ResponseEntity<Products>(list, HttpStatus.OK);
	}
}
