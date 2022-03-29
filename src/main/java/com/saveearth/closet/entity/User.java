package com.saveearth.closet.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;
    private String password;
    private String profile;
    private String address;

    @OneToMany(mappedBy = "user")
    //@JsonBackReference // 무한 루프 제거
    private List<Item> items;

    @OneToMany(mappedBy = "sender")
    @JsonBackReference(value = "user-mess")
    private List<Message> messages = new ArrayList<>();


}
