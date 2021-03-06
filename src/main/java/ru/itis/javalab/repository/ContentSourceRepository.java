package ru.itis.javalab.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itis.javalab.model.ContentSource;
import ru.itis.javalab.model.User;

import java.util.Optional;

public interface ContentSourceRepository extends JpaRepository<ContentSource, String> {
    Optional<ContentSource> findByIdAndMembersContaining(String id, User user);

    boolean existsByMembersContainsAndId(User user, String id);

    Page<ContentSource> findByIdContains(String id, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "insert into user_source values (:id, :username)")
    void addMember(String id, String username);
}
