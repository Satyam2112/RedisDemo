package com.api.redis.models;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {
    private String userId;
    private String name;
    private String email;
    private String phone;


}
