package com.example.survey.documents;

//import lombok.Data;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Created by adamo198 on 26/05/2023.
 * Github:adamo198
 */
//@Document
@Data
@Entity
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_id")
	private long id;
	private String title;
	private String email;

	@OneToMany(fetch = FetchType.LAZY,
			orphanRemoval = true,
			cascade = CascadeType.ALL)
	private List<Question> questions;

}