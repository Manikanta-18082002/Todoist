package net.javaguides.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    //To transfer data b/w client (POSTMAN) & server (Controller Layer) we use DTO
//    instead of returning the entity with sensitive information directly to the client,
//    we can use DTO to transfer only the required amount of data from, server to the client.

    private Long id;
    private String title;
    private  String description;
    private boolean completed;

}
