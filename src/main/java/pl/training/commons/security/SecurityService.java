package pl.training.commons.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

// w nowych wersjach Vaadin można użyć gotowej klasy AuthenticationContext
@Component
public class SecurityService {

    private static final String LOGOUT_SUCCESS_URL = "/";

    public Optional<UserDetails> getAuthenticatedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(this::toUserDetails);
    }

    private UserDetails toUserDetails(Object principal) {
        if (principal instanceof UserDetails userDetails) {
            return userDetails;
        } else {
            return null;
        }
    }

    public void logout() {
        UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
        var logoutHandler = new SecurityContextLogoutHandler();
        var request = VaadinServletRequest.getCurrent().getHttpServletRequest();
        logoutHandler.logout(request, null, null);
    }

}
