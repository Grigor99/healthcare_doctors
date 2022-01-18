package com.example.healthcare.util.uri;

public interface AuthUri {
    String BASE = "/healthcare/api/v1";

    String REGISTER = BASE + "/sign-up";
    String REGISTER_CONFIRM = REGISTER + "/confirm/{code}";
    String LOGIN = BASE + "/sign-in";
}
