package com.nvz.secubank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Role entity stores roles associated to a User
 * ManyToMany relationship with User
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
