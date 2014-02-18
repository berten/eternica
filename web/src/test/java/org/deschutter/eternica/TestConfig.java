package org.deschutter.eternica;

import org.deschutter.authentication.user.UserRepository;
import org.deschutter.eternica.character.CharacterDao;
import org.deschutter.eternica.character.CharacterService;
import org.deschutter.eternica.race.RaceDao;
import org.deschutter.user.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

	@Bean
	UserRepository userRepository() {
		return mock(UserRepository.class);
	}

	@Bean
    CharacterDao characterRepository() {
		return mock(CharacterDao.class);
	}

	@Bean
	UserDao userDao() {
		return mock(UserDao.class);
	}
    @Bean
    RaceDao raceDao() {
        return mock(RaceDao.class);
    }

    @Bean
    CharacterService characterService() {
        return mock(CharacterService.class);
    }
}
