package com.carbon.urlshortener.service;

import com.carbon.urlshortener.UrlshortenerApplication;
import com.carbon.urlshortener.exceptions.InvalidIDException;
import com.carbon.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(classes = UrlshortenerApplication.class)
class UrlServiceTest {

    @Autowired
    private UrlService urlService;
    private String localUrl;
    private String longUrl;
    private String id;
    private String result;


    @BeforeEach
    void setUp() throws Exception {
        //Build Valid Data
        localUrl = "http://localhost:8080/shortener";
        longUrl="https://www.examplesofgreatposts.com/2021/02/the--greate-and-ever-changing-effect-of-covid-in-todays-educational-system-in-nigeria-taiwo-chinedu-yusuf";
        id = "5JOzR3qC";
        result = urlService.shortenUrl(localUrl, longUrl);
    }

    @Test
    void testShortenUrlWithValidData() {
       assertNotNull(result);
    }

    @Test
    void testShortenUrlWithLongUrlAsNull() {
        Exception exception = assertThrows(NullPointerException.class, ()-> {urlService.shortenUrl(localUrl, null);});
        assertNotNull(exception.getMessage());

    }

   @Test
    void testGetLongURLWithValidID() {
        String result = urlService.getLongURLFromID(id);
        Assertions.assertEquals(longUrl, result);
    }


    @Test
    void testGetLongURLWithInvalidID() {
        id="38482ASS";
        Exception exception = assertThrows(InvalidIDException.class, ()-> {urlService.getLongURLFromID(id);});
        String expectedMessage = String.format("Invalid Id:::%s",id);
        String actualMessage=exception.getMessage();
            Assertions.assertEquals(expectedMessage,actualMessage);
        }
    }

