package com.norrissim.phonebook.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by User on 01.01.2018.
 */
public final class State {

    public static void populateModel(Model model, HttpServletRequest request) {
        model.addAttribute("__requestPath", getRequestPath(request));
        model.addAttribute("auth", getAuthState(request));
    }

    private static String getRequestPath(HttpServletRequest request) {
        String queryString = request.getQueryString();
        return request.getRequestURI() + (queryString == null ? "" : "?" + queryString);
    }

    public static Map<String, Object> getAuthState(HttpServletRequest request) {
        Optional<List<String>> optionalRoles = getRoles(request);

        return optionalRoles.map(roles -> {
            Map<String, Object> authState = new HashMap<>();
            authState.put("signedIn", !roles.contains("ROLE_ANONYMOUS"));
            authState.put("roles", roles);

            return authState;
        })
                .orElseGet(() -> {
                    Map<String, Object> authState = new HashMap<>();
                    authState.put("signedIn", false);
                    authState.put("roles", Collections.singletonList("ROLE_ANONYMOUS"));

                    return authState;
                });
    }

    private static Optional<List<String>> getRoles(HttpServletRequest request) {
        return getAuthentication(request)
                .map(a -> Functions.map(a.getAuthorities(), GrantedAuthority::getAuthority));
    }

    private static Optional<Authentication> getAuthentication(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            RequestAttributes requestAttributes = new ServletRequestAttributes(request);
            SecurityContext securityContext = (SecurityContext) requestAttributes.getAttribute("SPRING_SECURITY_CONTEXT", RequestAttributes.SCOPE_SESSION);
            if (securityContext == null) {
                securityContext = (SecurityContext) requestAttributes.getAttribute("SPRING_SECURITY_CONTEXT", RequestAttributes.SCOPE_GLOBAL_SESSION);
            }
            if (securityContext != null) {
                authentication = securityContext.getAuthentication();
            }
        }

        return Optional.ofNullable(authentication);
    }
}
