package com.study.java_springboot.java.repository;

import com.study.java_springboot.java.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

  MemberRepository repo = new MemoryMemberRepository();

  @AfterEach
  public void afterEach() {
    repo.clearStore();
  }

  @Test
  public void save() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    repo.save(member);

    // then
    Member result = repo.findById(member.getId()).get();

    Assertions.assertEquals(member, result);

    org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
  }

  @Test
  public void findByName() {
    // given
    Member member1 = new Member();
    member1.setName("spring1");
    repo.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repo.save(member2);

    // when
    List<Member> result = repo.findAll();

    // then
    org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
  }
}
