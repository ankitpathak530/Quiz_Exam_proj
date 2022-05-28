package com.exam.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

	private Long totalQuestion;
	private Long attemped;
	private Long nonAttemped;
	private Long correctAttemped;
	
	
}