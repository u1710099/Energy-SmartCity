package com.idigital.epam.energy.service;

import com.google.gson.Gson;
import com.idigital.epam.energy.common.CommonTestObjects;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.repository.RoleRepository;
import com.idigital.epam.energy.repository.UserRepository;
import com.idigital.epam.energy.security.JwtUtil;
import com.idigital.epam.energy.security.UserMaxsus;
import com.idigital.epam.energy.security.UserProvider;
import com.idigital.epam.energy.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {


    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserProvider userProvider;

    @Mock
    private JwtUtil jwtTokenUtil;

    @Mock
    private ResidentService residentService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(userRepository.findUserByCardNumber(anyString())).thenReturn(Optional.of(CommonTestObjects.getUserObject()));
        when(userRepository.save(any())).thenReturn(CommonTestObjects.getUserObject());
        String resident = new Gson().toJson(CommonTestObjects.getResponseResident());
        when(residentService.getResident(anyLong())).thenReturn(resident);
        doThrow(new RuntimeException()).when(userRepository).findUserByCardNumber(any());
    }

    @Test
    @DisplayName("Test user authentication without exception")
    public void testAuthentication() throws Exception {
        UserDetails userDetails = new UserMaxsus();
        UserMaxsus userRequest = new UserMaxsus(CommonTestObjects.getUserObject());
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userProvider.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtTokenUtil.generateToken(any(),anyBoolean())).thenReturn("Auth JWT");
        String auth = userService.authentication(userRequest);
        assertEquals("Auth JWT", auth);
    }

    @Test
    @DisplayName("Test user authentication with exception")
    public void testAuthenticationWithException() throws Exception {
        UserDetails userDetails = new UserMaxsus();
        UserMaxsus userRequest = new UserMaxsus(CommonTestObjects.getUserObject());
        doThrow(new RuntimeException())
                .when(authenticationManager).authenticate(any());
        when(userProvider.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtTokenUtil.generateToken(any(),anyBoolean())).thenReturn("Auth JWT");
        String auth = userService.authentication(userRequest);
        assertEquals("Auth JWT", auth);
    }

    @Test
    @DisplayName("Test user authentication with bad cred exception")
    public void testAuthenticationWithBadCredentialsException() {
        UserMaxsus userRequest = new UserMaxsus(CommonTestObjects.getUserObject());
        doThrow(new BadCredentialsException("INVALID_CREDENTIALS")).when(authenticationManager).authenticate(any());
        Exception ex = assertThrows(Exception.class, () -> userService.authentication(userRequest),"INVALID_CREDENTIALS");
        assertEquals("INVALID_CREDENTIALS", ex.getMessage());
    }

    @Test
    @DisplayName("Test user authentication with user disabled exception")
    public void testAuthenticationWithUserDisabledException() {
        UserMaxsus userRequest = new UserMaxsus(CommonTestObjects.getUserObject());
        doThrow(new DisabledException("USER_DISABLED")).when(authenticationManager).authenticate(any());
        Exception ex = assertThrows(Exception.class, () -> userService.authentication(userRequest),"USER_DISABLED");
        assertEquals("USER_DISABLED", ex.getMessage());
    }

    @Test
    @DisplayName("Test create user without exception")
    public void testCreateUserWithoutException() throws Exception {
        String resident = new Gson().toJson(CommonTestObjects.getResponseResident());
        when(residentService.getResident(anyLong())).thenReturn(resident);
        User user = userService.create(1000L, "password");
        assertNull( user);
    }

    @Test
    @DisplayName("Test create user without exception and empty user query")
    public void testCreateUserWithoutExceptionAndEmptyUser() throws Exception {
        User user = userService.create(1000L, "password");
        assertNull(user);
    }

    @Test
    @DisplayName("Test get principal")
    public void testGetCurrentUser(){
        when(userRepository.findUserByCardNumber(any())).thenReturn(Optional.of(CommonTestObjects.getUserObject()));
        User u = userService.getCurrentUser();
        assertNotNull(u);
    }
}
