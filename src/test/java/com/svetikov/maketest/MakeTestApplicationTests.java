package com.svetikov.maketest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svetikov.maketest.model.Person;
import com.svetikov.maketest.model.Status;
import com.svetikov.maketest.repository.PersonRepository;
import com.svetikov.maketest.service.PersonService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;


@WebMvcTest(AnyController.class)
class MakeTestApplicationTests {

    @MockBean
    private PersonService personService;
    @Autowired
    private MockMvc mockMvc;
    private Person joy;
    private Person person;
    private ObjectMapper objectMapper;

    @Before(value = "person")
    public void init() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..");
    }

    @Test
    void contextLoads() throws Exception {
        joy = new Person(1, "Joy2", Status.One);
        when(personService.findById(1)).thenReturn((joy));

        mockMvc.perform(get("/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));


    }

//    @BeforeAll
//    public void createPersonOne() {
//        System.out.println("teat >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
//    }

    @Test
    public void createPerson() throws Exception {

        Person person = new Person(7, "Volly", Status.One);

//        objectMapper = new ObjectMapper();
//        this.mockMvc.perform(post("/person")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(person)))
//                .andDo(print())
//                .andExpect(status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/person")
                .content(asJsonString(person))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPerson").exists());


//        RequestBuilder requestBuilder= MockMvcRequestBuilders
//                .post("/person")
//                .accept(MediaType.APPLICATION_JSON)
//                .content("{\"idPerson\"2,\"namePerson\":\"Velle\",\"statusPerson\":\"Status.One}")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result=mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                        .andReturn();

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
