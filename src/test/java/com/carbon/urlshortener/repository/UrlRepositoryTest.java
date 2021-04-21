package com.carbon.urlshortener.repository;

import com.carbon.urlshortener.UrlshortenerApplication;
import com.carbon.urlshortener.entity.Url;
import com.carbon.urlshortener.utils.CommonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ActiveProfiles("test")
@SpringBootTest(classes = UrlshortenerApplication.class)
public class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;
    private Long id;
    private Url savedUrl;

    @BeforeEach
    void setUp(){
        id = CommonUtils.createId();
        String longUrl = "https://www.examplesofgreatposts.com/2021/02/the--greate-and-ever-changing-effect-of-covid-in-todays-educational-system-in-nigeria-taiwo-chinedu-yusuf";
        savedUrl = urlRepository.save(new Url(id,longUrl));
    }

    @Test
    public void testSaveMethod(){
        assertNotNull(savedUrl);
        assertEquals(id,savedUrl.getId());
    }

    @Test
    public void testFindByIdMethod(){
        Optional<Url> foundEntity = urlRepository.findById(id);
        assertNotNull(foundEntity.get());
        assertEquals(foundEntity.get().getId(),id);
    }
}
