package org.deschutter.omen.character;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceImplTest {
    @InjectMocks
    private CharacterService characterService = new CharacterServiceImpl();

    @Mock
    private CharacterRepository characterRepository;

    @Test
    public void getCharactersForUserId() {
        org.deschutter.omen.character.Character character1 = new Character(1L, "CharacterName1", "Lineage1");
        Character character2 = new Character(2L, "CharacterName2", "Lineage1");
        when(characterRepository.findByUserId(123L)).thenReturn(Arrays.asList(character1, character2));
        assertThat(characterService.getCharactersForUserID(123L), hasItem(new CharacterDTO(1L,"CharacterName1", "Lineage1")));
        assertThat(characterService.getCharactersForUserID(123L), hasItem(new CharacterDTO(2L,"CharacterName2", "Lineage1")));
    }

    @Test
    public void getCharacter () {
        Character character = new Character(1L, "CharacterName1", "Lineage1");
        when(characterRepository.findById(1L)).thenReturn(character);
        assertThat(characterService.getCharacter(1L), allOf(hasProperty("id",is(1L)),hasProperty("characterName",is("CharacterName1")),hasProperty("lineageName",is("Lineage1"))));
    }
}