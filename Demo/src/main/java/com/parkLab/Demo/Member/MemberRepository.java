package com.parkLab.Demo.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long>{

//    @Query ("select m from Member m where m.id = :id and m.password =:password")
    Member findByIdAndPassword(String id, String password);
}
