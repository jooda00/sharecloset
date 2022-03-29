package com.saveearth.closet.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@Table(name = "item")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JsonManagedReference
    @JsonIgnore
    // api조회시 user리스트 띄우지 않기 위함
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String image;
    private String imagepath;
    private int category;
    private int size;
    private int price;
    // T/F로
    private Boolean isDirected;
    private Boolean isSended;
    private Boolean isCompleted;
    private LocalDateTime date;

    @OneToOne(mappedBy = "item", fetch = FetchType.EAGER)
    @JsonBackReference
    private ItemLike itemLike;

    public void setUser(User user){
        this.user = user;
        user.getItems().add(this);
    }


}
