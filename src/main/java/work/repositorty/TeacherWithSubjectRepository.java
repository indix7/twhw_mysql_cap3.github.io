package work.repositorty;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.entity.Subject;
import work.entity.Teacher;
import work.entity.TeacherWithSubject;

import java.util.List;

@Repository
public interface TeacherWithSubjectRepository extends CrudRepository<TeacherWithSubject, Integer> {
    @Query("SELECT no, teacher_id, subject_id FROM teacher_subject WHERE teacher_id = :teacher_id")
    public List<TeacherWithSubject> getTeacherWithSubjectByTeacherId(@Param("teacher_id") int id);
}
