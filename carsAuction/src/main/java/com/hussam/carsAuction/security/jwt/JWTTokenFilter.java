package com.hussam.carsAuction.security.jwt;

import com.hussam.carsAuction.security.userService.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class JWTTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImp userDetailsService;
    @Autowired
    private JWTUtilities JWTUtilities;


    /**
     * filter each request and extract the token from the header and validate it
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String headerAuth = httpServletRequest.getHeader("Authorization");
            String jwt = null;
            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                jwt  =  headerAuth.substring(7);
            }
            if (jwt != null && JWTUtilities.validateJwtToken(jwt)) {
                String email = JWTUtilities.getEmailFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {

            System.out.println("Sorry the token is invalid");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
