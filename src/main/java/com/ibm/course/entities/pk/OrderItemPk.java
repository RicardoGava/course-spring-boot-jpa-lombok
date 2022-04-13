package com.ibm.course.entities.pk;

import com.ibm.course.entities.Order;
import com.ibm.course.entities.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
public class OrderItemPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;
}
