package com.imagine.gestionsoft.api.dto;

public class JwtDto {

	private String token;
	private String bearer = "Bearer";
	private String email;
	private String[] roles;

	public JwtDto() {
	}

	public JwtDto(String token, String email, String[] roles) {
		super();
		this.token = token;
		this.email = email;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
