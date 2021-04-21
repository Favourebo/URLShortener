package com.carbon.urlshortener.controller;

import com.carbon.urlshortener.api.ApiResponse;
import com.carbon.urlshortener.dto.UrlDTO;
import com.carbon.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UrlController {

    private final UrlService urlService;


    @PostMapping(value = "/shortener")
    public ApiResponse<String> shortenUrl(@RequestBody @Valid UrlDTO urlDTO, HttpServletRequest request){
        return new ApiResponse<>(true,urlService.shortenUrl(request.getRequestURL().toString(), urlDTO.getLongUrl()));
    }

    @GetMapping(value = "/{id}")
    public RedirectView redirectUrl(@PathVariable String id){
        String redirectUrlString = urlService.getLongURLFromID(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);
        return redirectView;
    }

}

