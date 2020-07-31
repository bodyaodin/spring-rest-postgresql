package com.project.university.security;

import com.project.university.models.Student;
import com.project.university.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AuthProviderImpl implements AuthenticationProvider {

    private final StudentRepository studentRepository;

    @Autowired
    public AuthProviderImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Student student = checkStudentExistenceAndGet(authentication);
        String password = checkPasswordExistenceAndGet(authentication, student);
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new UsernamePasswordAuthenticationToken(student, null, authorities);
    }

    private Student checkStudentExistenceAndGet(Authentication authentication) {
        String email = authentication.getName();
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found! Please try again!"));
    }

    private String checkPasswordExistenceAndGet(Authentication authentication, Student student) {
        String password = authentication.getCredentials().toString();
        return Optional.of(password)
                .filter(p -> p.equals(String.valueOf(student.getReportCard())))
                .orElseThrow(() -> new BadCredentialsException("Bad credentials! Please try again!"));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
