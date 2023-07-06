package com.btagila.stockserver.resource;

import com.btagila.commons.domain.dto.UserRequestDto;
import com.btagila.commons.domain.dto.UserResponseDto;
import com.btagila.stockserver.config.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.btagila.stockserver.config.JwtRequestFilter.HEADER_STRING;
import static com.btagila.stockserver.config.JwtRequestFilter.TOKEN_PREFIX;


@RestController
public class AuthenticationResource {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationResource.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequestDto authenticationRequest) throws Exception {
        LOG.info("Authenticating user [{}]", authenticationRequest.getUsername());

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = new User(authenticationRequest.getUsername(), "x", List.of(new SimpleGrantedAuthority("USER")));

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new UserResponseDto(token));
    }

    @RequestMapping(value = "/check-auth", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkAuth(HttpServletRequest request) {
        try {
            String tokenHeader = request.getHeader(HEADER_STRING);
            if (tokenHeader != null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication == null || !authentication.isAuthenticated()) {
                    return ResponseEntity.ok(false);
                }

                String token = tokenHeader.replace(TOKEN_PREFIX, "").trim();
                String username = (String) authentication.getPrincipal();

                return ResponseEntity.ok(jwtTokenUtil.validateToken(token, username));
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
