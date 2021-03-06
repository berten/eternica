package org.deschutter.omen.index;

import org.deschutter.authentication.user.User;
import org.deschutter.omen.TestConfig;
import org.deschutter.omen.character.CharacterDTO;
import org.deschutter.omen.character.CharacterService;
import org.deschutter.omen.init.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, TestConfig.class})
@WebAppConfiguration
public class IndexControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private CharacterService characterService;
    private User user;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        user = mock(User.class);
        when(user.getUserId()).thenReturn(123L);
        when(characterService.getCharactersForUserID(123L)).thenReturn(Arrays.asList(new CharacterDTO(2L, "CharacterName1", "Lineage1", "Class", "ReligionName", "WealthName"), new CharacterDTO(2L, "CharacterName2", "Lineage1", "Class", "ReligionName", "WealthName")));
    }

    @Test
    public void index_NotLoggedIn() throws Exception {
        mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index")).andExpect(model().attributeDoesNotExist("characterName"));

    }

    @Test
    public void index_LoggedIn() throws Exception {
        mockMvc.perform(get("/index").principal(new UsernamePasswordAuthenticationToken(user, "password")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("characters", hasSize(2)))
                .andExpect(
                        model().attribute("characters",
                                hasItem(allOf(hasProperty("characterName", is("CharacterName1")), isA(CharacterDTO.class)))))
                .andExpect(
                        model().attribute("characters",
                                hasItem(allOf(hasProperty("characterName", is("CharacterName2")), isA(CharacterDTO.class)))));
    }

}
