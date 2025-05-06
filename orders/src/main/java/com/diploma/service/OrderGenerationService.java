package com.diploma.service;

import com.diploma.constants.OrderStatus;
import com.diploma.model.Location;
import com.diploma.model.Order;
import com.diploma.model.Product;
import com.diploma.repository.LocationRepository;
import com.diploma.repository.OrderRepository;
import com.diploma.repository.ProductRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
@Log4j2
public class OrderGenerationService {
    @Value("${order.batch-size}")
    private int batchSize;

    @Value("${order.quantity-size}")
    private int quantitySize;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LocationRepository locationRepository;

    Faker faker = new Faker(new Locale("en"));

    @Transactional
    public void generateOrders() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return;
        }
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty()) {
            return;
        }

        Random random = new Random();
        List<Order> ordersBatch = new ArrayList<>(batchSize);

        for (int i = 0; i < quantitySize; i++) {
            Product randomProduct = products.get(random.nextInt(products.size()));
            Location randomLocation = locations.get(random.nextInt(locations.size()));

            Order order = getRandomOrder(randomProduct, randomLocation, random);

            ordersBatch.add(order);


            if (ordersBatch.size() == batchSize) {
                orderRepository.saveAll(ordersBatch);
                ordersBatch.clear();
            }
        }

        if (!ordersBatch.isEmpty()) {
            orderRepository.saveAll(ordersBatch);
        }
    }

    private Order getRandomOrder(Product randomProduct,Location randomLocation, Random random) {
        int quantity = (random.nextInt(100) + 1) * 1000;
        Long totalPrice = randomProduct.getPrice() * quantity;

        Order order = new Order();
        order.setProduct(randomProduct);
        order.setPrice(totalPrice);
        order.setQuantity(quantity);
        order.setOrderDate(LocalDate.now());
        order.setCustomerName(faker.name().fullName());
        order.setStatus(OrderStatus.OPEN);
        order.setLocation(randomLocation);

        return order;
    }
}