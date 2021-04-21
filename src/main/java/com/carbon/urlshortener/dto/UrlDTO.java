package com.carbon.urlshortener.dto;

import com.carbon.urlshortener.utils.ConstantUtils;
import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
public class UrlDTO {

    @Pattern(regexp=ConstantUtils.URLPATTERN, message = "Url is Invalid!")
    private String longUrl;
}
