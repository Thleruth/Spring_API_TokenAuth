package mybatis.mappers;


import mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * Created by Thomas Leruth on 10/30/17
 */

@Mapper
public interface UserMapper {

    String GET_EVERYTHING = "SELECT * FROM test.users";
    String GET_EVERYTHING_ACTIVE = "SELECT * FROM test.users WHERE is_active = 1;";
    String GET_USER_BY_ID = "SELECT * FROM test.users WHERE id = #{id};";
    String GET_USER_BY_FNAME_AND_LNAME = "Select * FROM test.users WHERE first_name = #{first_name} AND " +
            "last_name = #{last_name}";
    String CREATE_USER = "INSERT INTO test.users (first_name, last_name, is_active)" +
            "VALUES (#{first_name}, #{last_name}, #{is_active});";
    String MODIFY_USER = "UPDATE test.users SET first_name = #{first_name}, " +
            "last_name = #{last_name}, is_active = #{is_active} WHERE id = #{id}";
    String DELETE_USER = "DELETE FROM test.users WHERE id = #{id}";

    @Select(GET_EVERYTHING)
    public ArrayList<User> getEverything();

    @Select(GET_EVERYTHING_ACTIVE)
    public ArrayList<User> getEverythingActive();

    @Select(GET_USER_BY_ID)
    public User getUserByID(int id);

    @Select(GET_USER_BY_FNAME_AND_LNAME)
    public User getUserByFNameAndLName(User user);

    @Insert(CREATE_USER)
    public int createUser(User user);

    @Update(MODIFY_USER)
    public int modifyUser(User user);

    @Delete(DELETE_USER)
    public int deleteUser(int id);
}
