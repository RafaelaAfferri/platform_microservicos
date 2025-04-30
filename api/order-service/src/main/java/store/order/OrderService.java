package store.order;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.product.ProductController;
import store.product.ProductOut;

@Service
public class OrderService {

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
        Order savedOrder = orderRepository.save(new OrderModel(order)).to();
        order.items().forEach(i -> {
            i.order(savedOrder);
            i.total(i.quantity() * i.product().price());
            Item savedeItem = itemRepository.save(new ItemModel(i)).to();
            savedOrder.items().add(savedeItem);
            
        });

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
