package com.example.memorybook.model.entity;


import com.example.memorybook.model.entity.abstract_entity.AbstractTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "club")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Club extends AbstractTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "club_id")
    private Long Club_id;

    @Column(name = "club_title")
    private String title;

    @Column(name = "club_info")
    private String info;

    @ManyToMany
    @JoinTable(name = "signed",
            joinColumns = @JoinColumn(name = "signed_club_id",referencedColumnName = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "signed_mem_id",referencedColumnName = "mem_id")
    )
    private List<Member> signed = new ArrayList<>();
}
