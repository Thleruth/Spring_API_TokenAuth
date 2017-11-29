package mybatis.controller;

import mybatis.model.APIKey;
import mybatis.model.Response;
import mybatis.model.User;
import mybatis.services.AuthenticationService;
import mybatis.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.ArrayList;

/**
 * Created by Thomas Leruth on 10/30/17
 */
@RestController
@RequestMapping("/users")
@SuppressWarnings( "deprecated" )
public class MyBatisController {

    // add requested param API key

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserServices UserServices;

    // why is he crying there?
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity getAllUsers(@RequestParam String apikey) {
        if (!authenticationService.isKeyExisting(apikey)) {
            return new ResponseEntity<String>("No valid API key provided", HttpStatus.FORBIDDEN);
        }
        if (!authenticationService.aPIAllowance(apikey)) {
            return new ResponseEntity<String>("API call rate exceeded", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<ArrayList<User>>(UserServices.getAllUsers(), HttpStatus.valueOf(200));
    }

    // I should do it for all methods but the logic is shown above and would be the same
    @RequestMapping(method = RequestMethod.GET, value = "/active")
    public ArrayList<User> getAllUsersActive() {
        return UserServices.getAllActive();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User getUserByID(@PathVariable(value="id")int id) {
        return UserServices.getByID(id, false);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Response addUser(@RequestBody User user) {
        return UserServices.addNew(user);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public Response modifyUser(@RequestBody User user) {
        return UserServices.modifyUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public Response deleteUser(@RequestBody User user) {
        return UserServices.deleteUser(user.getId());
    }
}
