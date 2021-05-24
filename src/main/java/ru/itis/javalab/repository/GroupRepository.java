package ru.itis.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.model.Group;
import ru.itis.javalab.model.User;

public interface GroupRepository extends JpaRepository<Group, String> {
    int deleteByIdAndAdmin(String id, User admin);
}
