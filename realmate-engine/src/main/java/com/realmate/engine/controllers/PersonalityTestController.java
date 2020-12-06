package com.realmate.engine.controllers;


import com.realmate.engine.models.Personality;
import com.realmate.engine.models.Question;
import com.realmate.engine.models.User;
import com.realmate.engine.payload.request.ExtQuizRequest;
import com.realmate.engine.payload.request.ExtQuizRequestBase;
import com.realmate.engine.payload.request.QuizRequest;
import com.realmate.engine.payload.response.ExtQuizResponse;
import com.realmate.engine.payload.response.ExtQuizResponseUser;
import com.realmate.engine.payload.response.MessageResponse;
import com.realmate.engine.repository.PersonalityRepository;
import com.realmate.engine.repository.QuizRepository;
import com.realmate.engine.repository.UserRepository;
import com.realmate.engine.security.StatefulRestTemplateInterceptor;
import com.realmate.engine.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/realmate/quiz")
public class PersonalityTestController {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonalityRepository personalityRepository;
    @Autowired
    JwtUtils jwtUtils;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @PostMapping("/answer")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> answerQuestions(@Valid @RequestBody QuizRequest request, @RequestHeader (name="Authorization") String token ) {
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
        ResponseEntity<ExtQuizResponse> result = restTemplate.exchange(
                "https://www.16personalities.com/api/session", HttpMethod.GET, sessionEntity,
                ExtQuizResponse.class);
        User currentUser = jwtUtils.getUserFromJwtToken(token.substring(7));
        Personality personality = new Personality();
        ExtQuizResponseUser extQuizResponseUser = result.getBody().getUser();
        LocalDateTime now = LocalDateTime.now();
        if(isNull(currentUser.getPersonality())) {
            personality.setUser(currentUser);
            personality.setPersonality(extQuizResponseUser.getPersonality());
            personality.setQuizDate(dtf.format(now));
            personality.setVariant(extQuizResponseUser.getVariant());
            personality.setMindScore(extQuizResponseUser.getScores().get(0));
            personality.setEnergyScore(extQuizResponseUser.getScores().get(1));
            personality.setNatureScore(extQuizResponseUser.getScores().get(2));
            personality.setTacticalScore(extQuizResponseUser.getScores().get(3));
            personality.setIdentityScore(extQuizResponseUser.getScores().get(4));
            currentUser.setPersonality(personality);
            personalityRepository.save(personality);
            userRepository.save(currentUser);
            return ResponseEntity.ok(new MessageResponse("Personalità utente salvata con successo"));
        } else {
            MessageResponse response = new MessageResponse("A questo utente risulta già assegnata una personalità. Aggiornamento non eseguito.");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/questions")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(this.quizRepository.findAll());
    }

}
