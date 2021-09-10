package dev.patika.app.dao.abstracts;

import dev.patika.app.entity.concretes.VisitingResearcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitingInstructorDao extends JpaRepository<VisitingResearcher, Long> {
    VisitingResearcher findById(long id);
}
