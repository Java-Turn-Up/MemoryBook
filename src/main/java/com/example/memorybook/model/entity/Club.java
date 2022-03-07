package com.example.memorybook.model.entity;


import com.example.memorybook.model.entity.abstract_entity.AbstractTimeEntity;
import lombok.*;

import javax.persistence.*;

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
}
