package com.ga.controller;

import com.ga.entity.UserRole;
import com.ga.service.UserRoleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//import com.ga.RoleController;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

    private MockMvc mockMvc;
    private UserRole newRole = new UserRole();

    @InjectMocks
    RoleController roleController;

//    @Mock
//    UserRole userRole;

    @Mock
    UserRoleServiceImpl userRoleService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
        newRole.setRoleId(1);
        newRole.setName("ROLE_QA");
    }

    @Test
    public void addRole_UserRole_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/role")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"ROLE_QA2\"}");
//            .content("not a thing");

        when(userRoleService.createRole(any())).thenReturn(newRole);
//        when(userRoleService.createRole(any())).thenReturn("{\"roleId\":1,\"name\":\"ROLE_QA2\"}");

        // TODO: is this even right?  yes we're testing that the controller returns something, but we just mocked everything
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"roleId\":1,\"name\":\"ROLE_QA\"}"))
                .andReturn();

        assertEquals(newRole.toString(), result.getResponse().getContentAsString());
    }

    @Test
    public void addRole_UserRole_Error() throws Exception {
        UserRole failRole = null;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/role")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":null");

        when(userRoleService.createRole(any())).thenReturn(null);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
//                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }

}
