package com.example.jobHunter.Auth;


import com.example.jobHunter.Entity.Job;
import com.example.jobHunter.Entity.JobPortalUser;
import com.example.jobHunter.dto.LoginRequestDto;
import com.example.jobHunter.dto.LoginResponseDto;
import com.example.jobHunter.dto.RegisterRequestDto;
import com.example.jobHunter.dto.UserDto;
import com.example.jobHunter.job.repository.JobPortalUserRepository;
import com.example.jobHunter.job.repository.RoleRepository;
import com.example.jobHunter.security.util.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final jwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
   private final JobPortalUserRepository jobPortalUserRepository;
  private final RoleRepository roleRepository;
   private final CompromisedPasswordChecker compromisedPasswordChecker;



    @PostMapping(value = "/login/public")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            var resultAuthentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(),
                    loginRequestDto.password()));

            String jwtToken = jwtUtil.generateJwtToken(resultAuthentication);
            var userDto = new UserDto();
            var loggedInUser = (JobPortalUser) resultAuthentication.getPrincipal();
            BeanUtils.copyProperties(loggedInUser,userDto);
            userDto.setRole(loggedInUser.getRole().getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(),
                            userDto, jwtToken));
        } catch (BadCredentialsException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED,
                    "Invalid username or password");
        } catch (Exception ex) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.valueOf(ex));
        }

    }

    @PostMapping(value = "/register/public")
    public ResponseEntity<String> apiRegister(@RequestBody RegisterRequestDto registerRequestDto) {

        JobPortalUser jobPortalUser = new JobPortalUser();
        BeanUtils.copyProperties(registerRequestDto,jobPortalUser);
        jobPortalUser.setPasswordHash(passwordEncoder.encode(registerRequestDto.password()));
        roleRepository.findById(1L).ifPresent(jobPortalUser::setRole);
        jobPortalUserRepository.save(jobPortalUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered successfully");

    }




    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status,
                                                                String message) {
        return ResponseEntity
                .status(status)
                .body(new LoginResponseDto(message, null, null));
    }
}
