package edu.java.clients;

import edu.java.dto.StackOverflowQuestionResponse;
import java.util.List;

public interface StackOverflowClient {
    List<StackOverflowQuestionResponse> fetchQuestion(String questionId);
}
