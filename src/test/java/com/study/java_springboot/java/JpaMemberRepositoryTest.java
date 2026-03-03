package com.study.java_springboot.java;

import com.study.java_springboot.java.domain.Member;
import com.study.java_springboot.java.repository.JpaMemberRepository;
import com.study.java_springboot.java.repository.MemberRepository;
import com.study.java_springboot.java.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class JpaMemberRepositoryTest {

  @Autowired
  MemberService memberService;

  @Test
  public void save() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    Long savedId = memberService.join(member);

    // then
    Member foundMember = memberService.findMembers().stream()
        .filter(m -> m.getId().equals(savedId))
        .findFirst()
        .orElse(null);

    assertNotNull(foundMember);
    assertEquals(member.getName(), foundMember.getName());
  }

}
