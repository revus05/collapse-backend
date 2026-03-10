package com.example.collapse.config;

import com.example.collapse.enums.Role;
import java.io.Serializable;

public record JwtUserPrincipal(String uuid, Role role) implements Serializable {}
