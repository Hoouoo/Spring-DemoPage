package com.parkLab.Demo.Controller;

import com.parkLab.Demo.Member.Member;
import com.parkLab.Demo.Member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j // 로그남기는 프레임워크
@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    private HttpSession session;

    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @RequestMapping("/")
    public String login(HttpServletRequest request){

        session = request.getSession();
        if(session.getAttribute("id") != null){
            return "welcome";
        }
        return "login";

    }

    @PostMapping("/signIn")
    public String signIn(HttpServletRequest request, String inputId, String inputPassword){
        log.info("id : {}, pw : {}", inputId, inputPassword);
        Member member = this.memberRepository.findByIdAndPassword(inputId, inputPassword);
        if (member != null){  // 정보가 있다면
            session = request.getSession();  // 세션을 생성한다.
            session.setAttribute("id", inputId);  // id로 세션을 생성한다.
            System.out.println("session = " + session.getAttribute("id"));
            return "loginSuccess";
        }
            return "loginFalse";
    }

    @RequestMapping("/signUp")
    public String SignUp(){
        return "signUp";
    }

    // MemberController에 회원가입 DB저장 기능 추가
    @RequestMapping("/signUp/create")
    public String create(Member member){
        System.out.println("member = " + member);
        this.memberRepository.save(member);
        return "login";
    }

    @PostMapping("/logOut")
    public String logOut(HttpServletRequest request, String id){

        if (session != null) {
            session.removeAttribute("id");
            System.out.println("session is removed");
            System.out.println("session = " + session.getAttribute("id"));
        }
        return "login";
    }
}
