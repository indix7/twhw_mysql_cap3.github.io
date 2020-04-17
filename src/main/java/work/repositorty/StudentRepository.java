package work.repositorty;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("SELECT id, name, age, gender FROM student WHERE name = :name")
    public Student getStudentByName(@Param("name") String name);

    @Query("INSERT INTO student (id, name, age, gender) VALUE (:id, :name, :age, :gender)")
    @Modifying
    public void saveStudent(@Param("id")int id, @Param("name")String name, @Param("age")int age, @Param("gender")String gender);
}
