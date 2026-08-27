package com.cafe.cafeBackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String name ;
    private String phone;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)

    private String password;
}
