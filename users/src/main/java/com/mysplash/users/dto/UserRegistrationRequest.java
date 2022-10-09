package com.mysplash.users.dto;

public  record UserRegistrationRequest(String firstName,
                                             String lastName,
                                             String email) {
}