package be.archilios.library.controllers;

import be.archilios.library.models.DomainException;
import be.archilios.library.models.User;
import be.archilios.library.repositories.InMemoryUserRepository;
import be.archilios.library.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static be.archilios.library.controllers.JsonTestUtils.read;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private InMemoryUserRepository repository;
    
    @TestConfiguration
    static class TestConfig {
        @Bean
        public UserService service(InMemoryUserRepository repo) {
            return new UserService(repo);
        }
    }
    
    @Test
    void givenUserListExists_whenApiCallForUsersIsMade_thanReturnAllUsers() throws Exception {
        when(repository.findAllUsers()).thenReturn(
                Arrays.asList(
                    new User("Nick Bauters", "1234abCD9", "nick@archilios.be", 33),
                    new User("Kelly de Lange", "8765zyXW0", "kelly@archilios.be", 26)
                )
        );
        
        
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(read("users/200_get_all_users")))
        ;
                
    }
    
    @Test
    void givenUserListExistsButIsEmpty_whenApiCallForUsersIsMade_thanReturnEmptyList() throws Exception {
        when(repository.findAllUsers()).thenReturn(
                List.of()
        );
        
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(read("200_get_empty_list")));
    }
    
    @Test
    void givenServerError_whenApiCallForUserIsMade_thanReturnServerErrorMessage() throws Exception {
        when(repository.findAllUsers()).thenThrow(new DomainException("Something went wrong"));
        
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json(read("500_server_error")));
    }
    
    @Test
    void givenUserListContainingAdultsAndChildren_whenApiCallFoAdultsIsMade_thanReturnOnlyAdults() throws Exception {
        when(repository.findAllUsersOlderThan(anyInt())).thenReturn(
                Arrays.asList(
                        new User(1L, "Nick Bauters", "1234abCD9", "nick@archilios.be", 33),
                        new User(3L, "Kelly de Lange", "8765zyXW0", "kelly@archilios.be", 26)
                )
        );

        mockMvc.perform(get("/users/adults").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(read("users/200_get_all_adults")));
    }

    @Test
    void givenUserListContainingAdultsAndChildrenIsEmpty_whenApiCallForAdultsIsMade_thanReturnEmptyList() throws Exception {
        when(repository.findAllUsersOlderThan(anyInt())).thenReturn(
                List.of()
        );

        mockMvc.perform(get("/users/adults").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(read("200_get_empty_list")));
    }
    
    @Test
    void givenUserList_whenApiCallForUsersBetweenTwoAgesIsMade_thanReturnCorrectSubsetOfUsers() throws Exception {
        when(repository.findAllUsersWithAgeBetween(anyInt(), anyInt())).thenReturn(
                Arrays.asList(
                        new User(2L, "Fynn Bauters", "1234abCV9", "fynn@archilios.be", 2),
                        new User(3L, "Kelly de Lange", "8765zyXW0", "kelly@archilios.be", 26)
                )
        );
        
        mockMvc.perform(get("/users/age/1/30").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(read("users/200_get_all_users_between_1_and_30")));
    }
    
    @Test
    void givenUserListIsEmpty_whenApiCallForAdultsIsMade_thanReturnEmptyList() throws Exception {
        when(repository.findAllUsersWithAgeBetween(anyInt(), anyInt())).thenReturn(
                List.of()
        );
        
        mockMvc.perform(get("/users/age/35/80").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(read("200_get_empty_list")));
    }
    
    @Test
    void givenMaxAgeSmallerThanMinAge_whenApiCallForUsersIsMade_thanReturnErrorMessage() throws Exception {
        mockMvc.perform(get("/users/age/35/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(read("users/400_min_larger_than_max")));
    }
    
    @Test
    void givenAgeIsOutOfBounds_whenApiCallForUsersIsMade_thanReturnErrorMessage() throws Exception {
        int maxAgeBound = 151;
        int minAgeBound = -1;
        
        mockMvc.perform(get("/users/age/0/" + maxAgeBound).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(read("users/400_age_out_of_bound")));
        
        mockMvc.perform(get("/users/age/" + minAgeBound + "/150").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(read("users/400_age_out_of_bound")));
    }
    
    @Test
    void givenPartialName_whenApiCallForUsersIsMadeWithName_thanReturnAllUsersContainingThatName() throws Exception {
        when(repository.findAllUsersByName(any())).thenReturn(
                Arrays.asList(
                        new User(1L, "Nick Bauters", "1234abCD9", "nick@archilios.be", 33),
                        new User(2L, "Fynn Bauters", "1234abCV9", "fynn@archilios.be", 2)
                )
        );
        
        mockMvc.perform(get("/users?name=Bauters").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(read("users/200_get_all_name_containing")));
    }
    
    @Test
    void givenPartialNameNotInUserList_whenApiCallDorUsersIsMadeWithName_thanReturn404UserNotFound() throws Exception {
        when(repository.findAllUsersByName(any())).thenReturn(List.of());
        
        mockMvc.perform(get("/users?name=Nope").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(read("users/404_user_not_found")));
    }
}
