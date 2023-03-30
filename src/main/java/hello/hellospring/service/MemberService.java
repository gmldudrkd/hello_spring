package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Service :: 비지니스 로직이 들어가는 부분, 가입등...

@Service // spring이 올라올때 서비스를 등록
public class MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        //MemberRepository 를 new 로 선언하는게 아닌 외부(테스트)에서 넣어서 사용할 수 있또록, 같은 역할인데 New 로 계속선언 시 문제생김
        //직접 선언 안하고 외부에서 넣어줌 > Defendency injection = DI
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        //회원가입

        //Optional<Member> result = memberRepository.findByname(member.getName()); // cmd+opt+v > 리턴 자동완성
        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // ifPresent 같은 로직이 있을 경우 메소드로 빼는게 좋다, ctrl+t
        memberRepository.findByname(member.getName()).ifPresent(m -> {
                //값이 있으면 노출 > ifPresent : Null 이 아닐경우, Optional을 사용했기 때문에 객체를 한번 감싸고 반환해 If not null 사용안해도 된다.
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    public List<Member> findMembers(){
        //전체회원 조회
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
