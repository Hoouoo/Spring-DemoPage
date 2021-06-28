package com.parkLab.Demo.Member;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor // Lombok의 기본생성자 자동 생성
@Getter
@Setter
@Entity  // Jpa 애노테이션으로 객체와 테이블을 매핑
@Table(name = "Member") // 엔티티와 매핑할 테이블을 저장
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // = AutoIncrement
    @Column(name = "seq")
    private Long seq;

    @NotEmpty
    private String id;
    @NotEmpty
    private String password;

}
