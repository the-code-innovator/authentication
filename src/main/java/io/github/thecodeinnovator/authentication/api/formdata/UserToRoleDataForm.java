package io.github.thecodeinnovator.authentication.api.formdata;

import lombok.Data;

@Data
public class UserToRoleDataForm {
    private String username;
    private String rolename;
}