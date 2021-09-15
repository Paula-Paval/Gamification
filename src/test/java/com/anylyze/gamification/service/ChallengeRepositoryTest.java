//package com.anylyze.gamification.service;
//
//import com.anylyze.gamification.model.Challenge;
//import com.anylyze.gamification.repository.ChallengeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@Transactional
//public class ChallengeRepositoryTest {
//
//    @Autowired
//    private ChallengeRepository challengeRepository;
//
//
//
//    @Test
//    public void testCreateGetAll() {
//        Challenge challenge = new Challenge(1L, "test", "descr", 40L);
//        challengeRepository.create(challenge);
//        List<Challenge> all = challengeRepository.getAll();
//        assertThat(all.get(0).equals(challenge)).isTrue();
//        assertEquals(all.get(0),challenge);
//    }
//
//
//}
