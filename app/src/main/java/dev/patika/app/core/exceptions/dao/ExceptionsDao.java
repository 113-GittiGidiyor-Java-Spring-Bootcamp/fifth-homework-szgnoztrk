package dev.patika.app.core.exceptions.dao;

import dev.patika.app.core.exceptions.entity.Exception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionsDao extends JpaRepository<Exception, Long> {
}
