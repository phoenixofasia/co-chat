package com.cochat.sso.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser {

    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    @Setter
    private boolean verified;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(
            name="authorities",
            joinColumns = @JoinColumn(name="username")
    )
    @Column(name="authorities")
    private Set<String> authorities;
}
