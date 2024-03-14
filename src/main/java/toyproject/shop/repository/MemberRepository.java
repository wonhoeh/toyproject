package toyproject.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.shop.domain.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //select m from Member m where n.name = ?
    List<Member> findByName(String name);
}
