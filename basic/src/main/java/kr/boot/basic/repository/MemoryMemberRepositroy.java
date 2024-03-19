package kr.boot.basic.repository;

import kr.boot.basic.domain.Member;

import java.util.*;

// DAO
public class MemoryMemberRepositroy implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; // 연속적인

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member>  findByName(String name) {
        return store.values()
                    .stream()
                    .filter(m -> m.getName().equals(name))
                    .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
