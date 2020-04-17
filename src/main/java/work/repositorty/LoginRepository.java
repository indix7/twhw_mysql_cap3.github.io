package work.repositorty;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import work.entity.Login;

public interface LoginRepository extends CrudRepository<Login, String> {
    @Query("SELECT id, password, authority FROM login WHERE id = :id AND password = :password")
    public Login getLoginByIdAndPassword(@Param("id")String id, @Param("password")String password);
}
