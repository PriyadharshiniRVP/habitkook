package com.habit.habit_kook.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "habits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // "Gym", "DSA", "Read"
    private String userId;      // "user1"
    private LocalDateTime completedAt;
    private Integer mood;       // 1-10
    private boolean success = true;
}

