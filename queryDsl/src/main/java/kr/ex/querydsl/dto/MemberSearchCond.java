package kr.ex.querydsl.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchCond {
    // 회원명, 팀명, 나이(크거나, 작거나)
    private String username;
    private String teamName;
    private Integer ageGoe; // getter or Equal
    private Integer ageLoe; // less or Equal
}
