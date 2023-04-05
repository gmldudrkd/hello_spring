package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //JpaRepository > 스프링데이터 jpa 가 해당 인터페이스를 보고 빈에 올림

    //findByname : 공통할 수 없는 필드를 사용 할 경우 > findByemail, findByid 등 메서드 이름을 변경가능
    @Override
    Optional<Member> findByname(String name); //select m from Member m where m.name =?
}
