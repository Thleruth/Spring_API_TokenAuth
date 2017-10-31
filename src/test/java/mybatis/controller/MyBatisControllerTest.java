package mybatis.controller;

import mybatis.Application;
import mybatis.model.Response;
import mybatis.model.User;
import mybatis.services.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class MyBatisControllerTest {

    //an idea could be to run those test on a local dataset instead of playing with the real one

    @Autowired
    UserServices services;

    User userTest = new User("a", "b",1 );

    @Test
    public void getAllUsers() throws Exception {
        List<User> list = services.getAllUsers();
        assertTrue(list.size() > 0);
    }

    @Test
    public void getAllUsersActive() throws Exception {
        List<User> list = services.getAllActive();
        assertTrue(list.size() > 0);
    }

    @Test
    public void addUser() throws Exception {
        Response response = services.addNew(userTest);
        assertTrue(response.getStatus() == 200);
        services.deleteUser(response.getData().getId());
    }

    @Test
    public void modifyUser() throws Exception {
        Response ResponseA = services.addNew(userTest);
        User userTestB = new User(ResponseA.getData().getId(),"b", "b",1 );
        Response responseB = services.modifyUser(userTestB);
        assertTrue(responseB.getStatus() == 200);
        services.deleteUser(responseB.getData().getId());
    }

    @Test
    public void deleteUser() throws Exception {
        Response responseA = services.addNew(userTest);
        Response responseB = services.deleteUser(responseA.getData().getId());
        assertTrue(responseB.getStatus() == 200);
    }

}