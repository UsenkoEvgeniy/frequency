package com.ue.frequency;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FrequencyController.class)
class FrequencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FrequencyService frequencyService;

    @SneakyThrows
    @Test
    void getFrequency_whenNoParam_returnBadRequest() {
        mockMvc.perform(get("/freq"))
                .andExpect(status().isBadRequest());
        verify(frequencyService, never()).getFrequency(anyString());
    }

    @SneakyThrows
    @Test
    void getFrequency_whenValidParam_callGetFrequencyAndReturnJson() {
        String str = "aaabbbb";
        Map<Character, Long> expectedMap = new LinkedHashMap<>();
        expectedMap.put('b', 4L);
        expectedMap.put('a', 3L);
        when(frequencyService.getFrequency(str)).thenReturn(expectedMap);
        String response = mockMvc.perform(get("/freq?str={str}", str))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        verify(frequencyService).getFrequency(str);
        assertEquals(objectMapper.writeValueAsString(expectedMap), response);
    }
}