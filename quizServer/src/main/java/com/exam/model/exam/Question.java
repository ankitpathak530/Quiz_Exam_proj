package com.exam.model.exam;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long quesId;
	
	@Column(length=5000)
	private String content;
	
	private String image;
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
