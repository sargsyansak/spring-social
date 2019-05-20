package itspace.com.social.facebookapi.controller;

import itspace.com.social.facebookapi.model.UserBean;
import itspace.com.social.facebookapi.providers.GoogleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    GoogleProvider googleProvider;

    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public String loginToGoogle(Model model) {
        return googleProvider.getGoogleUserData(model, new UserBean());
    }

    @RequestMapping(value = { "/","/login" })
    public String login() {
        return "login";
    }
}
