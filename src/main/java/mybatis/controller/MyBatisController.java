package mybatis.controller;

import mybatis.model.Response;
import mybatis.model.User;
import mybatis.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.ArrayList;

/**
 * Created by Thomas Leruth on 10/30/17
 */
@RestController
@RequestMapping("/users")
public class MyBatisController {

    @Autowired
    UserServices services;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ArrayList<User> getAllUsers() {
        return services.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/active")
    public ArrayList<User> getAllUsersActive() {
        return services.getAllActive();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User getUserByID(@PathVariable(value="id")int id) {
        return services.getByID(id, false);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Response addUser(@RequestBody User user) {
        return services.addNew(user);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public Response modifyUser(@RequestBody User user) {
        return services.modifyUser(user);
    }

    // (@RequestParam(value = "id") int id)
    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public Response deleteUser(@RequestBody User user) {
        return services.deleteUser(user.getId());
    }






}
