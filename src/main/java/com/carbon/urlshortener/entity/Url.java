package com.carbon.urlshortener.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

@RedisHash("url")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Url implements Serializable {

    @Id
    @NonNull
    private Long id;

    @NonNull
    private String longUrl;
}
