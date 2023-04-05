package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
//통합 테스트 >> 스프링 , 디비, 컨테이너가 올라가있는 상태의 테스트

@SpringBootTest
@Transactional //테스트 완료 후 롤백해줌
class MemberServiceIntegrationTest {
    //테스트 할 클래스 명에 cmd+shift+t >> 자동으로 테스트 페이지 생성

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given //hello 아이디를
        Member member = new Member();
        member.setName("spring2");

        //when //hello 로 회원가입 시
        Long saveId = memberService.join(member);

        //then //회원가입한 아이디가 있는가?
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
//        try{
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //try catch 대용으로 사용하는, memberService.join(member2) 이로직 실행 시 IllegalStateException 이 발생해야한다.
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}