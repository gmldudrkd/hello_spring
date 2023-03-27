package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //model 에 담긴 내용을 view 에서 받아서 사용하는 용도
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") //페이지 접속 시 url
    public String hello(Model model){
        model.addAttribute("data", "Hello!");
        return "hello"; //리턴 값 문자 반환 시 view resolver 가 template 폴더의 파일을 찾아 반환 (return 문자.html)
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){ // RequestParam 외부에서 파람을 받을 경우
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //응답 바디에 텍스트가 그대로 내려감, 위의 함수와 다른점은 뷰를 거치지 않는다!
    public String helloString(@RequestParam("name") String name){
        return "hello "+name; //리턴 값 문자내용 반환 시 view resolver 대신 httpmessageconverter 가 동작 -> 문자일 경우 그대로 출력
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello(); //객체로 응답할 경우 Json형태로 바뀜
        hello.setName(name);
        return hello; //리턴 값 문자내용 반환 시 view resolver 대신 httpmessageconverter 가 동작 -> 객체일 경우 json으로 변경
    }
    static class Hello{
        private String name; //private 로 선언되어 있어 밖에서 접근불가, 아래 get, set으로 접근, -> 프로퍼티 접근방식

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
