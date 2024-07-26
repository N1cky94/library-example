package be.archilios.library.controllers;

import be.archilios.library.repositories.InMemoryPublicationRepository;
import be.archilios.library.services.PublicationService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static be.archilios.library.controllers.JsonTestUtils.read;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicationController.class)
@Tag("integrationTest")
public class PublicationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private InMemoryPublicationRepository repository;
    
    @TestConfiguration
    static class TestConfig {
        @Bean
        public InMemoryPublicationRepository repository() {
            return new InMemoryPublicationRepository();
        }
        
        @Bean
        public PublicationService service(InMemoryPublicationRepository repository) {
            return new PublicationService(repository);
        }
    }
    
    @Test
    void givenNoCriteria_whenPublicationsApiIsCalled_thanReturn200WithAllPublications() throws Exception {
        mockMvc.perform(get("/publications"))
                .andExpect(status().isOk())
                .andExpect(content().json(read("publications/200_all")));
    }
    
    @Test
    void givenTitleCriteria_whenPublicationsApiIsCalled_thanReturn200WithAllPublicationsContainingTitle() throws Exception {
        mockMvc.perform(get("/publications?title=clean"))
                .andExpect(status().isOk())
                .andExpect(content().json(read("publications/200_all_with_title")));
    }
    
    @Test
    void givenTypeCriteria_whenPublicationsApiIsCalled_thanReturn200WithAllPublicationsWithType() throws Exception {
        mockMvc.perform(get("/publications?type=magazine"))
                .andExpect(status().isOk())
                .andExpect(content().json(read("publications/200_all_with_type")));
    }
    
    @Test
    void givenTitleAndTypeCriteria_whenPublicationsApiIsCalled_thanReturn200OkWithAllPublicationsContainingTitleAndWithType() throws Exception {
        mockMvc.perform(get("/publications?title=clean&type=magazine"))
                .andExpect(status().isOk())
                .andExpect(content().json(read("publications/200_all_with_title_and_type")));
    }
    
    @Test
    void givenNoCriteriaWithEmptyDataStore_whenPublicationsApiIsCalled_thanReturn200OkEmptyList() throws Exception {
        repository.clearDataStore();
        mockMvc.perform(get("/publications"))
                .andExpect(status().isOk())
                .andExpect(content().json(read("200_get_empty_list")));
        repository.resetDataStore();
    }
    
    @Test
    void givenTitleCriteria_whenPublicationsApiIsCalledWithoutMatch_thanReturn200OkEmptyList() throws Exception {
        mockMvc.perform(get("/publications?title=nope"))
                .andExpect(status().isOk())
                .andExpect(content().json(read("200_get_empty_list")));
    }
    
    @Test
    void givenTypeCriteria_whenPublicationsApiIsCalledWithoutMatch_thanReturn200OkEmptyList() throws Exception {
        
        mockMvc.perform(get("/publications?type=cd"))
                .andExpect(status().isOk())
                .andExpect(content().json(read("200_get_empty_list")));
    }
    
    @Test
    void givenTitleAndTypeCriteria_whenPublicationsApiIsCalledWithoutMatch_thanReturn200OkEmptyList() throws Exception {
        mockMvc.perform(get("/publications?title=nope&type=magazine"))
                .andExpect(status().isOk())
                .andExpect(content().json(read("200_get_empty_list")));
    }
}
