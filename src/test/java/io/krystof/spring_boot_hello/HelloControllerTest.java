package io.krystof.spring_boot_hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BuildProperties buildProperties;

    @Test
    void index_ReturnsGreetingWithVersion() throws Exception {
        Mockito.when(buildProperties.getVersion()).thenReturn("1.0.0-TEST");

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(org.hamcrest.Matchers.containsString("Greetings")))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("1.0.0-TEST")));
    }

    @Test
    void index_WithDelayParam_ReturnsOk() throws Exception {
        Mockito.when(buildProperties.getVersion()).thenReturn("1.0.0-TEST");

        mockMvc.perform(get("/").param("delayInMs", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string(org.hamcrest.Matchers.containsString("Greetings")));
    }
}
