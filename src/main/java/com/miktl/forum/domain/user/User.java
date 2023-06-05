package com.miktl.forum.domain.user;

import com.miktl.forum.dto.user.DataToRegisterUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "User")
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean status;
    private Boolean deleted;

    public User(DataToRegisterUser dataUser) {
        this.name=dataUser.name();
        this.email=dataUser.email();
        this.password=dataUser.password();
        this.role=Role.USER;
        this.status=false;
        this.deleted=false;
    }
    public void logicDelete() {
        this.deleted=true;
    }
}
