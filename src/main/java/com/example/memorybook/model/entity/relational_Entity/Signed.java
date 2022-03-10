package com.example.memorybook.model.entity.relational_Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "signed")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Signed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long id;

    @Column(name = "signed_club_id")
    private Long clubId;

    @Column(name = "signed_mem_id")
    private Long memId;

    @Column(name = "sigend_memtype")
    private Long memType;

    @Column(name = "joindate")
    private LocalDateTime joinedTime;
}
