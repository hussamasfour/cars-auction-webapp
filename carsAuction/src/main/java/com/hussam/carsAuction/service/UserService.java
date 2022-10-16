package com.hussam.carsAuction.service;

import com.hussam.carsAuction.entity.Role;
import com.hussam.carsAuction.entity.Type;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.exception.NotFoundException;
import com.hussam.carsAuction.exception.ResourceAlreadyInUseException;
import com.hussam.carsAuction.payload.request.LoginRequest;
import com.hussam.carsAuction.payload.request.SignUpRequest;
import com.hussam.carsAuction.payload.response.SignInResponse;
import com.hussam.carsAuction.repository.RoleRepository;
import com.hussam.carsAuction.repository.UserRepository;
//import com.hussam.carsAuction.security.jwt.JwtUtils;
import com.hussam.carsAuction.security.jwt.JWTUtilities;
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * class to provide implementation for user service interface
 */

@Slf4j
@Service
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtilities JWTUtilities;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method to get user from the database using id
     * @param user_id
     * @return User Object if found and null if not
     */
    @Override
    public User getUserById(Long user_id){
        log.info("Inside getUserById method from userService class");
        log.info("Finding the user with id: "+ user_id +" and return it if it is exist!");
        Optional<User> user = userRepository.findById(user_id);
        return user.orElseThrow(()->{
                log.error("There is no user registered with id: " +user_id);
                throw  new NotFoundException("user with id: "+ user_id +" not found");
        });
    }

    /**
     * method to find user by email
     * @param email
     * @return user if exist or null
     */
    @Override
    public User getUserByEmail(String email){
        log.info("Inside getUserByEmail method from userService class");
        log.info("Finding the user with email: "+ email +" and return the user Object");
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.orElseThrow(() -> {
            log.error("There is no user registered with email: " +email);
            throw new NotFoundException("User is not found with email " + email);
        });
    }


    /**
     * Method to register new user into the database
     * @param user
     * @return true if successes or false
     */
    @Override
    public User registerUser(SignUpRequest user){
        log.info("Inside registerUser method from userService class");
        String newUserEmail = user.getEmail();
        if (emailAlreadyExist(newUserEmail)) {
            log.error("There is existing user with this email: "+ newUserEmail);
            throw new ResourceAlreadyInUseException("Email", "address", newUserEmail);
        }
        Set<Role> roles = setNewUserRole(user.getRoles());

        log.info("Creating the new user object");
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(roles);
        log.info("Saving the new user information to the database");
        return userRepository.save(newUser);
    }

    /**
     * Method provide the implementation for login user
     * @param loginRequest
     * @return details of authenticated user
     */
    @Override
    public SignInResponse login(LoginRequest loginRequest) {
        log.info("Inside login method from userService class");
        log.info("Authenticating user email and password");
        Authentication authentication = authenticateUser(loginRequest);
        log.info("Setting the authenticated principle to the context");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Get the authenticated user details");
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
//
        String jwt = JWTUtilities.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        log.info("create sign in response object");
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setId(userDetails.getId());;
        signInResponse.setFirstName(userDetails.getFirstName());
        signInResponse.setEmail(userDetails.getUsername());
        signInResponse.setAccessToken(jwt);
        signInResponse.setRoles(roles);
        log.info("Return the sign in response");
        return signInResponse;
    }

    /**
     * Check if the given email is already in use in the databse repository or not
     * @param email
     * @return true if email is exist else false
     */
    boolean emailAlreadyExist(String email){
        log.info("Checking if the email: "+ email + " is already in use");
        return userRepository.existsByEmail(email);
    }

    public Authentication authenticateUser(LoginRequest loginRequest){
        log.info("Checking if the user email and password authenticated");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
    }

    /**
     * Method to find all the role for the new user
     * @param roles
     * @return set of user roles
     */
    public Set<Role> setNewUserRole(Set<String> roles){
        Set<Role> userRole = new HashSet<>();
        if(roles.isEmpty()){
            log.info("Setting the default role for the user");
            Role defaultRole = roleRepository.findByType(Type.ROLE_USER);

            userRole.add(defaultRole);
        }else{
            roles.forEach( role ->{
                if(role.equalsIgnoreCase("admin")){
                    log.info("Adding an ADMIN role for the user ");
                    userRole.add(roleRepository.findByType(Type.ROLE_ADMIN));
                }else{
                    log.info("Adding an USER role for the user ");
                    userRole.add(roleRepository.findByType(Type.ROLE_USER));
                }
            });
        }
        return userRole;
    }
}
