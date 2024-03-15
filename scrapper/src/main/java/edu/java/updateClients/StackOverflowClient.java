package edu.java.updateClients;

import edu.java.updateClients.updateDto.StackOverflowQuestionResponse;
import java.util.List;

public interface StackOverflowClient {
    List<StackOverflowQuestionResponse> fetchQuestion(String questionId);
}
