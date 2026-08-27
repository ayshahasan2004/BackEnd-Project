package com.cafe.cafeBackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_items")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String note;
    private Double price;

    @ManyToOne  /////(many items in one category (hot drinks has esspresso , coffe ,...))
    @JoinColumn(name = "category_id")////to make the relation
    private MenuCategory category;
}