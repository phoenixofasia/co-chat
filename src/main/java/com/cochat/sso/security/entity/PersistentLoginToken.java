package com.cochat.sso.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "persistent_logins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PersistentLoginToken {

    @Id
    private String username;
    private String series;
    private String token;
    private Date lastUsed;

}
