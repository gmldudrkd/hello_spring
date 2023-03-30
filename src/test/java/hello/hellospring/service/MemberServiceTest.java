package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //테스트 할 클래스 명에 cmd+shift+t >> 자동으로 테스트 페이지 생성

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        //실행 전 동작 메소드
        memberRepository = new MemoryMemberRepository(); // 선언 후
        memberService = new MemberService(memberRepository); // repository에 있는 메소드를 이용해 넣어준다.
    }

    @AfterEach
    public void afterEach(){
        //메서드실행이 끝나고 동작하는 메서드, 데이터 클렌징을 위해
        //테스트 시 메서드 순서에 의존관계없이 진행 (순서없음), 하나의 메서드가 끝난 뒤 공용 데이터에 영향을 줄 수 있어 클렌징이 필수!!!
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given //hello 아이디를
        Member member = new Member();
        member.setName("spring");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}