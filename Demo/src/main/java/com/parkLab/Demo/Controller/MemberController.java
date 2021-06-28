package com.parkLab.Demo.Controller;

import com.parkLab.Demo.Member.Member;
import com.parkLab.Demo.Member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // 로그남기는 프레임워크
@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/signIn")
    public String signIn(String inputId, String inputPassword){
        log.info("id : {}, pw : {}", inputId, inputPassword);
        Member member = this.memberRepository.findByIdAndPassword(inputId, inputPassword);
        if (member != null){
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
}
