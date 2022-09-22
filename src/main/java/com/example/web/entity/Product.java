package com.example.web.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "productId")
    private long productId;

    @NotNull
    @NotBlank(message = "Brand's name cannot be null")
    @Column(name = "name",nullable = false,length = 300)
    private String name;
    
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "price")
    private long price;
    
    @Column(name = "sale_price")
    private long salePrice;
    
    @Column(name = "slug",nullable = false)
    private String slug;
    
    @Column(name = "product_view")
    private int view;
    
    @Column(name = "total_sold")
    private long totalSold;
    
    @Column(name = "status")
    private int status;
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name= "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;


    @OneToMany(mappedBy = "product")
    private List<Comment> comments;


}
