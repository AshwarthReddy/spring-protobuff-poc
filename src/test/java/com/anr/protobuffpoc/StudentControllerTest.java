package com.anr.protobuffpoc;

import com.anr.protobuff.dto.StudentProto;
import com.google.protobuf.util.JsonFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class StudentControllerTest {

    @Autowired
    TestRestTemplate restTemplate;


    public static final String URL = "/api/v1/student";


    @Test
    public void getStudentById() throws Exception {
        var response = this.restTemplate.getForObject(URL + "/{id}", StudentProto.Student.class, 3);
        String jsonResponse = JsonFormat.printer().print(response);
        assertResponse(jsonResponse);


    }


    @Test
    public void addStudent() throws Exception {

        StudentProto.Student student = StudentProto.Student.newBuilder().setId(4).setFirstName("foo").setLastName("bar").build();

        ResponseEntity<StudentProto.Student> studentResponseEntity =
                this.restTemplate.postForEntity(URL + "/add", student, StudentProto.Student.class);


        assertEquals(200, studentResponseEntity.getStatusCodeValue());
        String jsonResponse = JsonFormat.printer().print(studentResponseEntity.getBody());
        assertResponse(jsonResponse);

    }

    private void assertResponse(String response) {
        assertThat(response, containsString("id"));
        assertThat(response, containsString("firstName"));
        assertThat(response, containsString("lastName"));
    }


}
