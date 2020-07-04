package com.pfe.municipalite.jwtauthetication.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ResetPwdForm {

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    
    private String lastPassword;


    public String getLastPassword() {
		return lastPassword;
	}

	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
