package kr.ex.querydsl.repository;

import kr.ex.querydsl.dto.MemberSearchCond;
import kr.ex.querydsl.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface MemberCusRepository {
    public List<MemberTeamDto> searchByBuilder(MemberSearchCond condition);
    public List<MemberTeamDto> search(MemberSearchCond condition);

    public Page<MemberTeamDto> searchPageSimple(MemberSearchCond conditon, Pageable pageable);

    public Page<MemberTeamDto> searchPageComplex(MemberSearchCond conditon, Pageable pageable);


}
