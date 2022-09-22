package com.example.web.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;
    
    @NotNull
    @NotBlank(message = "Brand's name cannot be null")
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "status",columnDefinition = "BOOLEAN")
    private boolean status;

    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    private Set<Product> products;
}
