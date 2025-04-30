package store.order;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import store.product.ProductOut;


@Entity
@Table(name = "item")
@Setter @Accessors(fluent = true)
@NoArgsConstructor
public class ItemModel {

    @Id
    @Column(name = "id_item")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nr_quantity")
    private int quantity;

    @Column(name = "nr_total")
    private Float total;

    @Column(name = "id_order")
    private String id_order;

    @Column(name = "id_product")
    private String id_product;

    public ItemModel(Item a) {
        this.id = a.id();
        this.quantity = a.quantity();
        this.total = a.total();
        this.id_order = a.order().id();
        this.id_product = a.product().id();
    }

    public Item to() {

        return Item.builder()
            .id(this.id)
            .quantity(this.quantity)
            .total(this.total)
            .order(Order.builder().id(this.id_order).build())
            .product(ProductOut.builder().id(this.id_product).build())
            .build();
        }
}
