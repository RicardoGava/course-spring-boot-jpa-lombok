package com.ibm.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibm.course.entities.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;
    private Integer orderStatus;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    @OneToMany(mappedBy = "id.order")
    @Setter(AccessLevel.NONE)
    private Set<OrderItem> items = new HashSet<>();
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        setOrderStatus(orderStatus);
        this.moment = moment;
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public Double getTotal() {
        double sum = 0.0;
        for(OrderItem i : items) {
            sum += i.getSubTotal();
        }
        return sum;
    }

}
