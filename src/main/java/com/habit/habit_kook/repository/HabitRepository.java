package com.habit.habit_kook.repository;

import com.habit.habit_kook.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserIdOrderByCompletedAtDesc(String userId);
    long countByUserId(String userId);
}

