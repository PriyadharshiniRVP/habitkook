package com.habit.habit_kook.controller;

import com.habit.habit_kook.model.Habit;
import com.habit.habit_kook.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HabitController {

    private final HabitRepository habitRepository;
    private final ChatClient chatClient;   // from org.springframework.ai.chat.client.ChatClient

    // POST /api/habits/log
    @PostMapping("/log")
    public ResponseEntity<String> logHabit(@RequestBody LogHabitRequest request) {
        Habit habit = new Habit();
        habit.setName(request.name());
        habit.setUserId(request.userId());
        habit.setMood(request.mood());
        habit.setCompletedAt(LocalDateTime.now());
        habit.setSuccess(true);

        habitRepository.save(habit);

        return ResponseEntity.ok("✅ " + request.name() + " logged! Mood: " + request.mood());
    }

    // GET /api/habits/list/{userId}
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Habit>> listHabits(@PathVariable String userId) {
        return ResponseEntity.ok(
                habitRepository.findByUserIdOrderByCompletedAtDesc(userId)
        );
    }

    // GET /api/habits/insights/{userId}
    @GetMapping("/insights/{userId}")
    public ResponseEntity<String> getInsights(@PathVariable String userId) {
        List<Habit> habits = habitRepository.findByUserIdOrderByCompletedAtDesc(userId);
        long totalHabits = habitRepository.countByUserId(userId);

        String prompt = """
            You are a supportive, universal habit coach.

            User ID: %s
            Total habits logged: %d
            Recent habit count: %d

            Give exactly:
            1. One key insight
            2. One tiny action (<5 minutes)
            3. One 30-second grounding tip

            Max 80 words.
            """
                .formatted(userId, totalHabits, habits.size());

        String insight = chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

        return ResponseEntity.ok(insight);
    }
}

/**
 * Simple DTO for the request body of /log
 * Make sure this is in the SAME file (at bottom) or in a separate file
 * in package com.habit.habit_kook.controller;
 */
record LogHabitRequest(String name, String userId, Integer mood) {}
