package store.order;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.product.ProductController;
import store.product.ProductOut;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductController productController;

    @Autowired
    private ItemRepository itemRepository;

    
    
    public Order create(Order order) {
        order.date(new Date());
        // aqui estah populando o produto
        order.items().forEach(i -> {
            ProductOut prod = productController.findById(i.product().id()).getBody();
            i.product(prod);
        });
        logger.debug("order: " + order.toString());
        final Order savedOrder = orderRepository.save(new OrderModel(order)).to();
        logger.debug("saved order: " + savedOrder.toString());
        order.items().forEach(i -> {
            i.order(savedOrder);
            i.total(i.quantity() * i.product().price());
            logger.debug("item: " + i.toString());
            Item savedItem = itemRepository.save(new ItemModel(i)).to();
            logger.debug("saved item: " + savedItem.toString());
            savedOrder.items().add(savedItem);
            logger.debug("saved order with items: " + savedOrder.toString());
            
        });
        logger.debug("returns saved order with items: " + savedOrder.toString());

        return savedOrder;
    }

    public Order findById(String id) {
        return orderRepository.findById(id).get().to();
    }

    public List<Order> findAll(String idAccount) {
        
        return StreamSupport
            .stream(orderRepository.findByIdAccount(idAccount).spliterator(), false)
            .map(OrderModel::to)
            .toList();
    }


}
