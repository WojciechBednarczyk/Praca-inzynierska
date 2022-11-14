package pl.edu.pwr.akademiatreningu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exercises")
@Getter
@Setter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Integer id;

    private String name;

    private String description;

    private String url;

    private Integer rating;

    private String muscleGroup;
}
