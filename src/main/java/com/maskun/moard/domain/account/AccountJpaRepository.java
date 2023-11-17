package com.maskun.moard.domain.account;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountJpaRepository extends CrudRepository<Account,String> {
    /* JPQL을 사용하여 쿼리를 했다. 네이티브 쿼리와는 다르게 DB에 비의존적이기 때문에 FUCTION함수로 DB에 해당 함수를 사용하라고 지정할 수 있었다.*/
    @Query("SELECT a FROM Account a WHERE a.accountId = :id AND a.accountPw = FUNCTION('PASSWORD',:pw)")
    Optional<Account> login(@Param("id")String id, @Param("pw")String pw);
}
