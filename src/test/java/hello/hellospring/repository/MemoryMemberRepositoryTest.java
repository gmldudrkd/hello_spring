package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions; //옵션+엔터로 static 선언시 assertions. 을 안써도 사용가능
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        //메서드실행이 끝나고 동작하는 메서드, 데이터 클렌징을 위해
        //테스트 시 메서드 순서에 의존관계없이 진행 (순서없음), 하나의 메서드가 끝난 뒤 공용 데이터에 영향을 줄 수 있어 클렌징이 필수!!!
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        //검증, setname에 넣은 값과 Result로 받은 값이 같은가?
        //Assertions.assertEquals(member, result);//같은지 비교,다를경우 Run시 붉은 불 (jupitor)
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //변수에 커서 후 shift+F6 > 동일한 변수 찾아줌, 복사 후 변수 명 변경 시 유용
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByname("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
