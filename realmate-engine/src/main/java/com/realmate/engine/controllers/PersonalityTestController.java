package com.realmate.engine.controllers;


import com.realmate.engine.models.Question;
import com.realmate.engine.payload.request.ExtQuizRequest;
import com.realmate.engine.payload.request.ExtQuizRequestBase;
import com.realmate.engine.payload.request.QuizRequest;
import com.realmate.engine.repository.QuizRepository;
import com.realmate.engine.security.StatefulRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/realmate/quiz")
public class PersonalityTestController {
    @Autowired
    QuizRepository quizRepository;

    @PostMapping("/answer")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> answerQuestions(@Valid @RequestBody QuizRequest request) {
        final String uri = "https://www.16personalities.com/it/risultati-del-test";


        RestTemplate restTemplate = new RestTemplate();
        StatefulRestTemplateInterceptor cookieInterceptor = new StatefulRestTemplateInterceptor();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(cookieInterceptor);
        restTemplate.setInterceptors(interceptors);
        List<ExtQuizRequestBase> questionList = new ArrayList<>();
        request.getQuestions().stream().forEach(question -> {
            ExtQuizRequestBase basequestion = new ExtQuizRequestBase();
            Question quizbase = this.quizRepository.findById(question.getId()).orElseThrow(() -> new RuntimeException("Impossibile trovare domanda con id "+question.getId()));
            basequestion.setAnswer(question.getAnswer());
            basequestion.setText(quizbase.getQuestion());
            questionList.add(basequestion);
        });
        ExtQuizRequest extRequest = new ExtQuizRequest();
        extRequest.setQuestions(questionList);
        extRequest.setGender(null);
        extRequest.setVersion(0);
        extRequest.setInvitecode("");
        System.out.println(extRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<ExtQuizRequest> entity = new HttpEntity<>(extRequest,headers);
        restTemplate.postForObject(uri, entity, Object.class);
        HttpEntity<ExtQuizRequest> sessionEntity = new HttpEntity<>(null,headers);

        return restTemplate.exchange(
                "https://www.16personalities.com/api/session", HttpMethod.GET, sessionEntity,  String.class);
    }

    @GetMapping("/questions")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(this.quizRepository.findAll());
    }

}
