package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //컨트롤러 선언 시 스프링이 뜰때 스프링컨테이너가 해당 컨트롤러를 객체로 들고있따 > 빈
public class MemberController {
    //private final MemberService memberService = new MemberService();
    // 같이 사용하는 서비스 인데 계속 New 선언 비효율적 아래와 같이 컨테이너에 등록해서 사용하기.
    private final MemberService memberService;

    @Autowired // 컨트롤러를 생성 시 스프링 빈에 등록된 서비스(memberservice)를 가져다 넣어준다(등록해준다). >> DI(의존성 주입)
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
