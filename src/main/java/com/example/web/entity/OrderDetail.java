package com.example.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderDetailId;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = true)
	private float total;
	
	@ManyToOne
	@JoinColumn(name= "orderId")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name= "productId")
	private Product product;
	
	
}
