package com.carbon.urlshortener.controller;

import com.carbon.urlshortener.dto.UrlDTO;
import com.carbon.urlshortener.service.UrlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@WebMvcTest(controllers = UrlController.class)
@ExtendWith(SpringExtension.class)
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UrlService urlService;
    private String longUrl;
    private String localUrl;
    private String shortenedUrl;
    private UrlDTO urlDTO;

   @BeforeEach
   public void setUp() throws Exception {
       longUrl="https://www.examplesofgreatposts.com/2021/02/the--greate-and-ever-changing-effect-of-covid-in-todays-educational-system-in-nigeria-taiwo-chinedu-yusuf";
       localUrl = "http://localhost/shortener";
       shortenedUrl="http:localhost:8080/5JOzR0QJ";
       urlDTO = new UrlDTO();
       urlDTO.setLongUrl(longUrl);

       //For Valid Request
       when(this.urlService.shortenUrl(localUrl,longUrl)).thenReturn("shortenedUrl");
       when(this.urlService.shortenUrl(localUrl,null)).thenThrow(NullPointerException.class);
       when(this.urlService.getLongURLFromID("5JOzR0QJ")).thenReturn(longUrl);
   }


    @Test
    void testShortenUrlWithValidInput_thenReturns200() throws Exception {
        mockMvc.perform(post("/shortener")
                 .content(objectMapper.writeValueAsString(urlDTO))
                 .contentType("application/json"))
                 .andExpect(status().isOk());
    }


    @Test
    void testShortenUrlWithInvalidInput_thenReturns400() throws Exception {
        urlDTO.setLongUrl(null);
        mockMvc.perform(post("/shortener")
                .content(objectMapper.writeValueAsString(urlDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testredirectUrlWithValidInput_thenReturns200() throws Exception {
        mockMvc.perform(get("/5JOzR0QJ")
                .contentType("application/json"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void redirectUrlWithInvalidInput_thenReturns400() throws Exception {
        urlDTO.setLongUrl(null);
        mockMvc.perform(post("/shortener")
                .content(objectMapper.writeValueAsString(urlDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

}
