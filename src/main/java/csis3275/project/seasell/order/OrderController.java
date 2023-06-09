package csis3275.project.seasell.order;

import csis3275.project.seasell.order.dto.CreateOrderDto;
import csis3275.project.seasell.order.dto.OrderDto;
import csis3275.project.seasell.order.dto.OrderStatusUpdateDto;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.repository.OrderRepository;
import csis3275.project.seasell.order.service.OrderService;
import csis3275.project.seasell.product.service.ProductService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto dto) throws IOException {
        orderService.addOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Retrieve a list of orders from the perspective of a buyer. Can be further filtered by product ID
     */
    @GetMapping
    public List<OrderDto> getOrders(@RequestParam(required = false) Integer productId) {
        return orderService.getOrders(productId);
    }

    @GetMapping("/products/{id}/buyer")
    public OrderDto getBuyerInfoByProductId(@PathVariable int id) {

        return orderService.getBuyerInfoById(id);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int id, @RequestBody OrderStatusUpdateDto dto) {
        orderService.updateOrderStatus(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
