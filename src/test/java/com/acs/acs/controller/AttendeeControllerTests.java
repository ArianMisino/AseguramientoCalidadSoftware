package com.acs.acs.controller;

import com.acs.acs.Attendee;
import com.acs.acs.AttendeeController;
import com.acs.acs.AttendeeService;
import com.acs.acs.AttendeeServiceTests;
import com.acs.acs.exception.EntityNotValidException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AttendeeController.class)
@WebAppConfiguration
@EnableWebMvc
public class AttendeeControllerTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @InjectMocks
    AttendeeController attendeeController;

    @Mock
    AttendeeService attendeeService = new AttendeeService();

    Attendee attendee = new Attendee(
            0,
            "31738285",
            "Mario",
            "Cuevas",
            "2604343704",
            new GregorianCalendar(1985, 10, 18),
            "https://www.clarin.com/img/2020/05/28/W8j5oIwMb_720x0__1.jpg",
            true);

    String attendeeJson = "{\n" +
            "  \"id\": 1,\n" +
            "  \"dni\": \"31738285\",\n" +
            "  \"name\": \"Mario\",\n" +
            "  \"surname\": \"Cuevas\",\n" +
            "  \"phone\": \"2604343704\",\n" +
            "  \"birthDate\": 501130800000,\n" +
            "  \"dniScanUrl\": \"https://www.clarin.com/img/2020/05/28/W8j5oIwMb_720x0__1.jpg\",\n" +
            "  \"approved\": true\n" +
            "}";

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenDNI_whenGetAttendeeByDNI_thenStatus200() throws Exception {
        //given
        when(attendeeService.getByDni("31738285")).thenReturn(attendee);

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/attendees/31738285").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        JSONAssert.assertEquals(attendeeJson, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void givenInvalidDNI_whenGetAttendeeByDNI_thenStatus400() throws Exception {
        //given
        when(attendeeService.getByDni("wegerhgsrh")).thenThrow(new EntityNotValidException());

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/attendees/wegerhgsrh").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(400);

    }
}

