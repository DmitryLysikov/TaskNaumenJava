package ru.dima.NaumenJava.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dima.NaumenJava.Task.Task;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository implements CrudRepository<Task, Long> {
    private final List<Task> tasks;

    @Autowired
    public TaskRepository(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void create(Task task) {
        tasks.add(task);
    }

    @Override
    public Task read(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void update(Task updatedTask) {
        Optional<Task> taskOptional = tasks.stream()
                .filter(task1 -> task1.getId().equals(updatedTask.getId()))
                .findFirst();

        taskOptional.ifPresent(task -> {
            task.setTaskName(updatedTask.getTaskName());
            task.setStatus(updatedTask.getStatus());
            task.setDeadlines(updatedTask.getDeadlines());
        });
    }

    @Override
    public void delete(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    @Override
    public List<Task> readAll() {
        return tasks;
    }
}
