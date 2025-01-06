package glsib.parkingauth.restControleur;



import glsib.parkingauth.dtos.LoginUserDto;
import glsib.parkingauth.entities.User;
import glsib.parkingauth.services.AuthenticationService;
import glsib.parkingauth.services.JwtService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth2")
public class RestAuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/lo")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            // Authenticate user
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            // Generate JWT Token
            String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getRole());

            // Return JWT Token and user information
            LoginResponse response = new LoginResponse(authenticatedUser.getEmail(), jwtToken);
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // Response DTO for the login response
    @Setter
    @Getter
    public static class LoginResponse {
        // Getters and Setters
        private String email;
        private String token;

        public LoginResponse(String email, String token) {
            this.email = email;
            this.token = token;
        }

    }
}
