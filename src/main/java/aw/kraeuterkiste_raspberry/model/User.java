package aw.kraeuterkiste_raspberry.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "id")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String password;
}
