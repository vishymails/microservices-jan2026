package com.bvr.demo.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvr.demo.models.Order;
import com.bvr.demo.models.Product;
import com.bvr.demo.repositories.OrderRepository;
import com.bvr.demo.repositories.ProductRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;



@RestController
@RequestMapping("/api/v1")
public class FintechController {

	
	private final ProductRepository productRepo;
	private final OrderRepository orderRepo;
	
	public FintechController(ProductRepository productRepo, OrderRepository orderRepo) {
		super();
		this.productRepo = productRepo;
		this.orderRepo = orderRepo;
	}
	
	
	@PostMapping("/process-trade")
	public String executeTrade() {
		// update inventory
		Product p = new Product();
		p.setSku("GOLD-ASSET-1");
		p.setStockLevel(100);
		productRepo.save(p);
		
		//create order in the same db 
		Order o = new Order();
		o.setProductId(p.getId());
		o.setStatus("COMPLETED");
		orderRepo.save(o);
		
		return "Shared DB transaction successful : Asset " + p.getSku() + "logged";
	}
	
	
	@PostMapping("/inventory")
	public Product createAsset(@RequestBody Product product) {
		return productRepo.save(product);
	}


	@PostMapping("/order")
	public String placeOrder(@RequestParam Long productId, @RequestParam Integer quantity) {
		return productRepo.findById(productId)
				.map(product -> {
					Order o = new Order();
					o.setProductId(productId);
					o.setStatus("PLACED");
					orderRepo.save(o);
					return "Order placed for product " + product.getSku() + " with quantity " + quantity;
				}).orElse("Product not found, order failed");
	}

	@GetMapping("/inventory")
	public List<Product> getAllAssets() {
		return productRepo.findAll();
	}

	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

}
