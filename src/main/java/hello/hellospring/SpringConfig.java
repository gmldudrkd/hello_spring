package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자동 컴포넌트 방식이 아닌 직접 빈등록 방식
// @bean 으로 서비스와 리포지토리를 등록, 서비스는 리포지토리를 포함할 수 있또록
// controller 는 자동컴포넌트로 선언해야함

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
