package com.example.healthcare.util.uri;

public interface AuthUri {
    String BASE = "/healthcare/api/v1";
    String AUTH = BASE + "/auth";

    String REGISTER = AUTH + "/sign-up";
    String REGISTER_CONFIRM = AUTH + "/confirm/{code}";
    String LOGIN = AUTH + "/sign-in";
}
