package com.cafe.cafeBackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "menu_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    ////cascade: any save/update/delete performed on MenuCategory
    ////is automatically applied to it related MenuItems too
    private List<MenuItem> items;
}