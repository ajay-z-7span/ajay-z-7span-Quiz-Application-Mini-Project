package com.miniproject.onlinequizapplication.dto.RequestDTO;

import java.util.List;

public record AnswerReq(Integer quizId, Integer questionId, List<Integer> optionId) {
}
