//package com.idigital.epam.energy.controller;
//
//
//import com.idigital.epam.energy.entity.User;
//import com.idigital.epam.energy.repository.UserRepository;
//import com.idigital.epam.energy.service.AddressService;
//import com.idigital.epam.energy.service.UserService;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hibernate.cfg.AvailableSettings.URL;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//
//    @Autowired
//    private UserController userController;
//    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
//
//    private static final String URL = "http://localhost:8080/api/user";
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    UserService userService;
//
//    @MockBean
//    UserRepository userRepository;
//
//    @MockBean
//    private AddressService addressService;
//
//
//    @Test
//
//    public void  getAll() throws Exception{
//        List<User>userList = new ArrayList<>();
//        User user = new User();
//        user.setId(1L);
//        user.setCardNumber("11111111");
//        user.setLastName("Any");
//        user.setFirstName("An");
//        user.setActive(true);
//
//        when(userService.getAll()).thenReturn(userList);
////        ResponseEntity<List<User>> res = userController.getAllUser();
////        assertNotNull(res);
//        Mockito.when(userService.getAll()).thenReturn(userList);
//        String url = "api/user/all";
//        RequestBuilder get;
//        mockMvc.perform(get(url))
//                        .andExpect(status().isOk());
//        assertNotNull(userList);
//

//    }
//    @Test
//    public void testUsers() {
//        MockHttpServletRequestBuilder all =  MockMvcRequestBuilders.get(URL + "http://localhost:8080/api/user/all");
//        MockHttpServletRequestBuilder byId =  MockMvcRequestBuilders.get(URL + "api/user/" + 1);
//
//        assertThat(all).isNotNull();
//
//    }

//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package com.idigital.epam.energy.controller;
////
////
////import com.idigital.epam.energy.entity.User;
////import com.idigital.epam.energy.repository.UserRepository;
////import com.idigital.epam.energy.service.AddressService;
////import com.idigital.epam.energy.service.UserService;
////import org.junit.Test;
////import org.mockito.Mockito;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
////import org.springframework.boot.test.mock.mockito.MockBean;
////import org.springframework.test.web.servlet.MockMvc;
////import org.springframework.test.web.servlet.RequestBuilder;
////import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
////import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
////
////import org.springframework.web.client.RestTemplate;
////
////import java.util.ArrayList;
////import java.util.List;
////
////import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
////import static org.hibernate.cfg.AvailableSettings.URL;
////import static org.junit.Assert.assertNotNull;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////@WebMvcTest(UserController.class)
////public class UserControllerTest {
////
////
////    @Autowired
////    private UserController userController;
////    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
////
////    private static final String URL = "http://localhost:8080/api/user";
////    @Autowired
////    private MockMvc mockMvc;
////
////    @MockBean
////    UserService userService;
////
////    @MockBean
////    UserRepository userRepository;
////
////    @MockBean
////    private AddressService addressService;
//
//
////@Test
////
////    public void  getAll() throws Exception{
////        List<User> userList = new ArrayList<>();
////        userList.add(new User("id:4","cardNumber:11111111","password:34533","firstName:Alex","LastName:Finn","active:true","role:admin"));
////        Mockito.when(userService.getAll()).thenReturn(userList);
////        String url = "api/user/all";
////        mockMvc.perform(get(url)).andExpect(status().isOk());
////        assertNotNull(userList);
//
////
////    }
////    @Test
////    public void testUsers() {
////        MockHttpServletRequestBuilder all =  MockMvcRequestBuilders.get(URL + "http://localhost:8080/api/user/all");
////        MockHttpServletRequestBuilder byId =  MockMvcRequestBuilders.get(URL + "api/user/" + 1);
////
////        assertThat(all).isNotNull();
////
////    }
////
////
////
////}
