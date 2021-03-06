package org.deschutter.omen.character;


import java.util.List;

public interface CharacterService {
    List<CharacterDTO> getCharactersForUserID(Long userId);

    CharacterDTO getCharacter(Long characterId);
}
