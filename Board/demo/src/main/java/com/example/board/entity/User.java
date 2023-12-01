package com.example.board.entity;

import com.example.board.core.StringArrayConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(length = 50, nullable = false)
    private String password;
    @Column(length = 20, nullable = false)
    private String username;
    @Column(length = 30)
    @Convert(converter = StringArrayConverter.class)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Board> board =  new ArrayList<>();

    @Builder
    public User(int id, String email, String password, String username, String phoneNumber, List<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }
    public void output(){
        System.out.println(id);
        System.out.println(email);
        System.out.println(password);
        System.out.println(username);
        System.out.println(roles);
    }
}
