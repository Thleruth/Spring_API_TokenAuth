package mybatis.services;

import mybatis.mappers.APIKeyMapper;
import mybatis.model.APIKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Random;

import static mybatis.util.APIConstant.APIPERXSECONDS;

/**
 * Created by Thomas Leruth on 11/15/17
 */
@Service
public class AuthenticationService {

    @Autowired
    APIKeyMapper apiKeyMapper;

    /**
     * Create Hexodecimal key of 50 elements
     *
     * @return
     */
    public String apiKeyCreator() {
        Random r = new Random();
        StringBuilder sb;
        do {
            sb = new StringBuilder();
            while (sb.length() < 50) {
                sb.append(Integer.toHexString(r.nextInt()));
            }
        } while (isKeyExisting(sb.toString()));
        return sb.toString().substring(0, 50);

    }

    /**
     * Create a APIKey Pojo (with id) and put the created key
     *
     * @param apiKey
     */
    public void saveApiKey(String apiKey) {
        apiKeyMapper.createAPIKey(new APIKey(apiKey));
    }

    public boolean isKeyExisting(String apiKey) {
        if (null == apiKeyMapper.getByAPIKey(apiKey)) {
            return false;
        }
        return true;
    }

    public boolean aPIAllowance(String aPIKey) {
        APIKey key = apiKeyMapper.getByAPIKey(aPIKey);

        long callTime = System.currentTimeMillis();
        long timeDifferenceInMillis = (new Timestamp(callTime).getTime() - key.getLastCallTime().getTime());
        long aPICallGain = timeDifferenceInMillis / 1000 / APIPERXSECONDS;

        key.setCallCounter(key.getCallCounter() - (int) aPICallGain >= 0 ?
                key.getCallCounter() - (int) aPICallGain : 0 );

        long APICallGainMillisRemainder = (timeDifferenceInMillis / 1000 % APIPERXSECONDS) * 1000;

        key.setLastCallTime(new Timestamp(callTime - APICallGainMillisRemainder));
        // A bit of a wrong trick, decreasing the call time to make sure the users does not loss unused wait seconds

        if (key.getCallCounter() < 20) {
            key.setCallCounter(key.getCallCounter()+1);
            apiKeyMapper.updateAPIKEY(key);
            return true;
        }
        return false;
    }
}
