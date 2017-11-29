package mybatis.controller;

import jdk.nashorn.internal.parser.JSONParser;
import mybatis.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Thomas Leruth on 11/15/17
 */

@Controller
@RequestMapping("/api")
public class ApiController {



    @Autowired
    private AuthenticationService authenticationService;

    // not pretty for the response but does the job
    @RequestMapping(method = RequestMethod.POST, value = "/creator")
    public ResponseEntity createApiKey() {
        String apiKey = authenticationService.apiKeyCreator(); // Create key
        authenticationService.saveApiKey(apiKey); // save the key
        return new ResponseEntity<String>(JSONParser.quote(("API KEY: ").concat(apiKey)), HttpStatus.valueOf(201));
    }
}
