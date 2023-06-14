package com.example.survey.documents;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String type;

    @OneToMany(fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Option> options;

}
