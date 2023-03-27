package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") //페이지 접속 시 url
    public String hello(Model model){
        model.addAttribute("data", "Hello!");
        return "hello"; //리턴 값 문자 반환 시 view resolver 가 template 폴더의 파일을 찾아 반환 (return 문자.html)
    }
}
