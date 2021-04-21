package com.carbon.urlshortener.dto;

import com.carbon.urlshortener.UrlshortenerApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = UrlshortenerApplication.class)
@ActiveProfiles("test")
public class UrlDTOTest {

    private Validator validator;
    private UrlDTO urlDTO;
    private String validLongUrl;

    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        urlDTO = new UrlDTO();
    }

    @Test
    public void testValidationWhenAnValidUrlIsPassed(){
        validLongUrl="https://stackoverflow.com/questions/34115213/redis-noauth-authentication-required-but-there-is-no-password-setting";
        urlDTO.setLongUrl(validLongUrl);
        Set violations = validator.validate(urlDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testValidationWhenNoUrlIsPassed(){
        urlDTO.setLongUrl("");
        Set violations = validator.validate(urlDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testValidationWhenAnInvalidUrlIsPassed(){
        urlDTO.setLongUrl("Htpp:/I am ivalidUrl");
        Set violations = validator.validate(urlDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testGetterAndSetterMethodWithValidLongUrl(){
         urlDTO.setLongUrl(validLongUrl);
         String actualResult = urlDTO.getLongUrl();
         String expectedResult = validLongUrl;
        Assertions.assertEquals(expectedResult,actualResult);
    }
}
