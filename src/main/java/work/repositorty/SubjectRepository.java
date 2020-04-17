package work.repositorty;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.entity.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {
    @Query("SELECT id, name FROM subject WHERE name = :name")
    public Subject getSubjectByName(@Param("name")String name);

    @Query("INSERT INTO subject (id, name) VALUE (:id, :name) ")
    @Modifying
    public void saveSubject(@Param("id")int id, @Param("name")String name);
}
