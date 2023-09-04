package net.javaguides.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Provides Primary key as an Auto Increase
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
    private boolean completed;
}
