package mybatis.services;

import mybatis.mappers.UserMapper;
import mybatis.model.Response;
import mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Thomas Leruth on 10/30/17
 */

@Service
public class UserServices {

    @Autowired
    UserMapper userMapper;

    Response response = new Response();

    public ArrayList<User> getAllUsers() {
        return userMapper.getEverything();
    }

    public ArrayList<User> getAllActive() {
        return userMapper.getEverythingActive();
    }

    // this method is only used to get the correct data name in the return type of add (since no ID)
    public User getUserByFNameAndLName(User user) {
        return userMapper.getUserByFNameAndLName(user);
    }

    // I added a boolean as some method with sanity check call this method, thus, it is a way to double run sanity
    public User getByID(int id, boolean skipSanity) {

        if (skipSanity || sanityCheck(id)) {
            return userMapper.getUserByID(id);
        }

        return new User();
    }

    public Response addNew(User user) {

        if (sanityCheck(user)) {
            userMapper.createUser(user);
            responseSuccess(response, getUserByFNameAndLName(user));
        }
        else {
            responseFailure(response);
        }
        return response;
    }

    public Response modifyUser(User user) {

        if (sanityCheck(user) && sanityCheck(user.getId())) {
            userMapper.modifyUser(user);
            responseSuccess(response, getByID(user.getId(),true));
        }
        else {
            responseFailure(response);
        }
        return response;
    }

    public Response deleteUser(int id){

        if (sanityCheck(id)) {
            responseSuccess(response, getByID(id, true));
            userMapper.deleteUser(id);
        }
        else {
            responseFailure(response);
        }
        return response;
    }

    public boolean sanityCheck(User user) {
        try {
            if (user.getFirst_name() == null || user.getLast_name() == null) {
                throw new Exception();
            }
        }
        catch (Exception x) {
            x.printStackTrace();
            return false;
        }
        return true;
    }

    //overloading this method
    public boolean sanityCheck(int id) {

        try {
            ArrayList<User> users = getAllUsers();
            for (User p : users) {
                if (p.getId() == id) {
                    return true;
                }
            }
            throw new Exception();
        }
        catch (Exception x) {
            x.printStackTrace();
            return false;
        }
    }

    public void responseSuccess(Response response, User user){
        response.setStatus(200);
        response.setMessage("Success");
        response.setData(user);
    }

    public void responseFailure(Response response) {
        response.setStatus(400);
        response.setMessage("Failure");
        response.setData(null);
    }

}
