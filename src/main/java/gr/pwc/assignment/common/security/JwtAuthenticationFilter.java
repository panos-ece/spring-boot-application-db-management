package gr.pwc.assignment.common.security;

import gr.pwc.assignment.authorizedpersonnel.controller.OperatorController;
import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import gr.pwc.assignment.authorizedpersonnel.service.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                     @NonNull HttpServletResponse response,
                                     @NonNull FilterChain filterChain)
            throws ServletException, IOException {

//        LOG.info("I am here");
        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                //            LOG.info("I am there");
                String token = authHeader.substring(7);
                String username = this.jwtUtil.extractUsername(token);

                if (username != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null) {

                    AuthorizedPersonnel authPersonnel = this.userDetailsService.loadUserByUsername(username);

                    if (this.jwtUtil.validateToken(token, authPersonnel)) {
                        LOG.info("Token is valid");
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        authPersonnel,
                                        null,
                                        authPersonnel.getAuthorities()
                                );

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
            //        LOG.info("After operation");
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException | IllegalArgumentException | JwtException ex) {
            LOG.warn("Authentication failed: {}", ex.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: " + ex.getMessage());
        } catch (Exception ex) {
            LOG.error("Unexpected error in JWT filter", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}
