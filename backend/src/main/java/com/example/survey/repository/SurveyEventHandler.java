
package com.example.survey.repository;

import com.example.survey.authstuff.User;
import com.example.survey.documents.Survey;
import com.example.survey.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Survey.class)
public class SurveyEventHandler {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @HandleBeforeCreate
    public void handleSurveyCreate(Survey survey) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userDetails.getUsername()));

//        System.out.println("\t\tuser is " + user.getUsername());
        survey.setOwner(user);
    }
}