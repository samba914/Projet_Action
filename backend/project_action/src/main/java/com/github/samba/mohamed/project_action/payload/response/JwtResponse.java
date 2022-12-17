package com.github.samba.mohamed.project_action.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtResponse {

    private Long id;
    private String username;
    private String email;
    private String surname;
    private String name;
    private String token;

}
