package work.repositorty;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("SELECT no, student_id, teacher_id, subject_id, score FROM course WHERE student_id = :student_id")
    public List<Course> getCoursesByStudentId(@Param("student_id") int id);
    @Query("SELECT no, student_id, teacher_id, subject_id, score FROM course WHERE teacher_id = teacher_id")
    public List<Course> getCoursesByTeacherId(@Param("teacher_id") int id);
    @Query("UPDATE  course SET score = :score WHERE student_id = :student_id AND subject_id = :subject_id")
    @Modifying
    public void modifyScoreByIdAndSubjectId(@Param("student_id")int student_id,
                                            @Param("subject_id")int subject_id,
                                            @Param("score")double score);
}
