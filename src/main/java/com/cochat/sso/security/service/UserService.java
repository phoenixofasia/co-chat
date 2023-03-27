package com.cochat.sso.security.service;

import com.cochat.sso.security.entity.ChatUser;
import com.cochat.sso.security.model.UserDto;

public interface UserService {
    ChatUser create(UserDto userDto);
}
