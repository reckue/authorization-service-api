package com.reckue.account.service.impl;

import com.reckue.account.exception.AuthenticationException;
import com.reckue.account.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Class SecurityServiceImpl represents realization of SecurityService.
 *
 * @author Kamila Meshcheryakova
 */
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final TokenStore tokenStore;

    /**
     * The method allows to get all additional information from a token.
     * Throws {@link AuthenticationException} in case of invalid token.
     *
     * @param token user token
     * @return additional information from a token
     */
    @Override
    public Map<String, Object> getTokenInfo(String token) {
        try {
            return tokenStore.readAccessToken(token).getAdditionalInformation();
        } catch (Exception e) {
            throw new AuthenticationException("Invalid token", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * The method allows to check for token availability.
     * Throws {@link AuthenticationException} in case if the token is missing or
     * is too short.
     *
     * @param request information for HTTP servlets
     * @return access token
     */
    @Override
    public String checkToken(HttpServletRequest request) {
        String token;
        try {
            token = request.getHeader(AUTHORIZATION).substring(7);
            return token;
        } catch (NullPointerException e) {
            throw new AuthenticationException("Token missing", HttpStatus.BAD_REQUEST);
        } catch (StringIndexOutOfBoundsException e) {
            throw new AuthenticationException("Token is too short", HttpStatus.BAD_REQUEST);
        }
    }
}
