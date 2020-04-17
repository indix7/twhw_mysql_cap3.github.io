package work.repositorty;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.entity.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    @Query("SELECT id, name FROM teacher WHERE name = :name")
    public Teacher getTeacherByName(@Param("name") String name);
}
