package com.patnox.config.controllers;

import com.patnox.config.services.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConfigController.class)
//@AutoConfigureMockMvc
@WithMockUser
@Slf4j
class ConfigControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ConfigService configService;

    @MockBean
    private ConfigController configController;

//    @Before("")
//    public void setup()
//    {
//        //Init MockMvc Object and build
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Test
    void configsTest() throws Exception
    {
        //This allows testing POST and PUT
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        RequestBuilder request = MockMvcRequestBuilders.get("/configs");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        String newConfig = "{\n" +
                "    \"name\": \"datacenter-66\",\n" +
                "    \"metadata\": {\n" +
                "        \"monitoring\": {\n" +
                "            \"enabled\": true\n" +
                "        },\n" +
                "        \"limits\": {\n" +
                "            \"cpu\": {\n" +
                "                \"enabled\": true,\n" +
                "                \"value\": \"250m\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        request = MockMvcRequestBuilders.post("/configs")
                .content(newConfig)
                .contentType(MediaType.APPLICATION_JSON);
        result = mvc.perform(request).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        request = MockMvcRequestBuilders.get("/search?metadata.monitoring.enabled=true");
        result = mvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        //log.debug("Got test response as: " + response.getContentAsString(StandardCharsets.UTF_8));
        //assertEquals(response.getContentAsString(StandardCharsets.UTF_8), "nene");
        //JSONAssert.assertEquals(result.getResponse().getContentAsString(StandardCharsets.UTF_8), newConfig, JSONCompareMode.LENIENT);
    }
}