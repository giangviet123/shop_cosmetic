package com.example.web.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;
    
    @Column(name = "name",nullable = false,length = 300)
    private String name;
    
    @Column(name = "status",columnDefinition = "BOOLEAN")
    private boolean status;

    @ManyToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;
    
}
