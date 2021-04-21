package com.carbon.urlshortener.service;

import com.carbon.urlshortener.entity.Url;
import com.carbon.urlshortener.exceptions.InvalidIDException;
import com.carbon.urlshortener.repository.UrlRepository;
import com.carbon.urlshortener.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;


    public String shortenUrl(String localUrl, String longUrl){
        log.info("Shortening Url::{}", longUrl);
        Long id = CommonUtils.createId();

        urlRepository.save(new Url(id, longUrl));
        return String.format("%s%s", getBaseStringFromLocalUrl(localUrl), CommonUtils.base62Encode(id));
    }



    public String getLongURLFromID(String uniqueID){

        Url retrievedUrl = urlRepository.findById(CommonUtils.base62Decode(uniqueID)).orElseThrow(() ->
                new InvalidIDException(String.format("Invalid Id:::%s",uniqueID)));

        return retrievedUrl.getLongUrl();
    }




    private String getBaseStringFromLocalUrl(String localURL) {
        String[] addressComponents = localURL.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }
}
