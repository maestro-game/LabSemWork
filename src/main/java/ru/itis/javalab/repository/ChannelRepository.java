package ru.itis.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.model.Channel;
import ru.itis.javalab.model.User;

public interface ChannelRepository extends JpaRepository<Channel, String> {
    int deleteByIdAndAdmin(String id, User admin);
}
