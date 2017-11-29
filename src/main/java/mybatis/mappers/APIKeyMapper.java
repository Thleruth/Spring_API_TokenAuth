package mybatis.mappers;

import mybatis.model.APIKey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Thomas Leruth on 11/15/17
 */
@Mapper
public interface APIKeyMapper {

    String GET_BY_APIKEY = "Select * FROM test.apikeys WHERE apikey = #{apikey}";
    String CREATE_APIKEY = "INSERT INTO test.apikeys (apikey, call_counter, last_call_time) " +
            "VALUES (#{key}, #{callCounter}, #{lastCallTime})";
    String MODIFY_APIKEY = "UPDATE test.apikeys SET call_counter = #{callCounter}, " +
            "last_call_time = #{lastCallTime} WHERE apikey = #{key}";
    @Select(GET_BY_APIKEY)
    public APIKey getByAPIKey(String apikey);

    @Insert(CREATE_APIKEY)
    public int createAPIKey(APIKey aPIKey);

    @Update(MODIFY_APIKEY)
    public int updateAPIKEY(APIKey aPIKey);
}
