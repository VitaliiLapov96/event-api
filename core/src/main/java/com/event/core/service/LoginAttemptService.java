package com.event.core.service;

public interface LoginAttemptService {

    void loginFailed(String key);

    boolean isBlocked();

}
