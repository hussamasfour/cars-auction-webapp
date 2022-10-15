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
import com.hussam.carsAuction.security.userService.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * class to provide implementation for user service interface
 */

@Service
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method toget user from the database using id
     * @param user_id
     * @return User Object if found and null if not
     */
    @Override
    public User getUserById(Long user_id){
        Optional<User> user = userRepository.findById(user_id);
        return user.orElseThrow(()-> new NotFoundException("user with id: "+ user_id +" not found"));
    }

    /**
     * method to find user by email
     * @param email
     * @return user if exist or null
     */
    @Override
    public User getUserByEmail(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.orElseThrow(() -> new NotFoundException("User is not found with email " + email));
    }


    /**
     * Method to register new user into the database
     * @param user
     * @return true if successes or false
     */
    @Override
    public User registerUser(SignUpRequest user){
           String newUserEmail = user.getEmail();
           if (emailAlreadyExist(newUserEmail)) {
               throw new ResourceAlreadyInUseException("Email", "address", newUserEmail);
           }
           Set<Role> roles = new HashSet<>();
           Set<String> userRole = user.getRoles();

           if(userRole.isEmpty()){
               Role defaultRole = roleRepository.findByType(Type.ROLE_USER);
               roles.add(defaultRole);
           }else{
               userRole.forEach( role ->{
                   if(role.equalsIgnoreCase("admin")){
                       roles.add(roleRepository.findByType(Type.ROLE_ADMIN));
                   }else{
                       roles.add(roleRepository.findByType(Type.ROLE_USER));
                   }
               });
           }

           User newUser = new User();
           newUser.setFirstName(user.getFirstName());
           newUser.setLastName(user.getLastName());
           newUser.setEmail(user.getEmail());
           newUser.setPassword(passwordEncoder.encode(user.getPassword()));
           newUser.setRole(roles);
           return userRepository.save(newUser);
    }

    /**
     * Method provide the implementation for login user
     * @param loginRequest
     * @return details of authenticated user
     */
    @Override
    public SignInResponse login(LoginRequest loginRequest) {
            Authentication authentication = authenticateUser(loginRequest);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            SignInResponse signInResponse = new SignInResponse();
            signInResponse.setId(userDetails.getId());;
            signInResponse.setFirstName(userDetails.getFirstName());
            signInResponse.setEmail(userDetails.getUsername());
            signInResponse.setRoles(roles);

            return signInResponse;
    }

    /**
     * Check if the given email is already in use in the databse repository or not
     * @param email
     * @return true if email is exist else false
     */
    boolean emailAlreadyExist(String email){
        return userRepository.existsByEmail(email);
    }

    public Authentication authenticateUser(LoginRequest loginRequest){
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
    }
}
