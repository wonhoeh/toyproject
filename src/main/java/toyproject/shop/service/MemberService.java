package toyproject.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shop.domain.Member;
import toyproject.shop.exception.DataNotFoundException;
import toyproject.shop.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        validDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findById(member.getId());
        findMember.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isPresent()) {
            return findMember.get();
        } else {
            throw new DataNotFoundException("존재하지 않는 회원입니다.");
        }
    }

    @Transactional
    public void updateMember(Long memberId, String name) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if(findMember.isPresent()) {
            findMember.get().setName(name);
        } else {
            throw new DataNotFoundException("존재하지 않는 회원입니다.");
        }
    }
}
