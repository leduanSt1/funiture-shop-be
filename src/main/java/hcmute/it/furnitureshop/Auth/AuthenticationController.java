package hcmute.it.furnitureshop.Auth;



import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    UserServiceImpl userService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        if(userService.findByName(request.getUsername()).isEmpty()){
            request.setPhone(request.getUsername());
            return ResponseEntity.ok(authenticationService.register(request));
        }
        else{
            return null;
        }
    }
    @PostMapping("/resetPassword/{phone}")
    public ResponseEntity<AuthenticationResponse> resetPassword(@RequestBody AuthenticationRequest request, @PathVariable("phone")String phone){
        Optional<User> user=userService.findByName(phone);
        if(user.isPresent())
            return ResponseEntity.ok(authenticationService.resetPassword(request.getPassword(),user.get()));
        else
            return null;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/check")
    public Boolean check(
            @RequestBody AuthenticationRequest request
    ){
        return authenticationService.check(request);
    }
    @PostMapping("/login-gmail")
    public ResponseEntity<AuthenticationResponse> authenticateGmail(
            @RequestBody AuthenticationRequest request
    ){
        if(!userService.findByName(request.getUsername()).isPresent()){
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername(request.getUsername());
            registerRequest.setPassword(request.getPassword());
            registerRequest.setName(request.getUsername());
            registerRequest.setPhone(null);
            return ResponseEntity.status(201).body(authenticationService.register(registerRequest));
        }else{
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }
    }

}
