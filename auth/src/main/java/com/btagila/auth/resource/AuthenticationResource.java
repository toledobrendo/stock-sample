package com.btagila.auth.resource;

import com.btagila.auth.service.UserService;
import com.btagila.commons.domain.dto.UserRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationResource {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationResource.class);

    private UserService userService;

    public AuthenticationResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> doAuthenticate(@RequestBody UserRequestDto requestDto) throws Exception {
        LOG.info("Authenticating username=[{}]", requestDto.getUsername());
        userService.checkAuthentication(requestDto);
        return ResponseEntity.ok("OK");
    }
}
