package com.vaxi.spring_boot_optica.Model.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @JsonProperty("username")
    @NotNull
    private String username;
    @JsonProperty("password")
    @NotNull
    private String password;
}

