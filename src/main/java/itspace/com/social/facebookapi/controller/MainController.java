package itspace.com.social.facebookapi.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public MainController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping("/")
    public String feed(ModelMap model) {

        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }
        User profile = facebook.fetchObject("me", User.class, "id", "name", "link", "email");
        model.addAttribute("userProfile", profile);
        PagedList<Post> userFeed = facebook.feedOperations().getFeed();
        model.addAttribute("userFeed", userFeed);
        PagedList<Post> posts = facebook.feedOperations().getPosts();
        model.addAttribute("posts", posts);
        return "feed";
    }


//    @GetMapping("/friends")
//    public String friends(Model model) {
//        if (getModel(model)) {
//            return "redirect:/connect/facebook";
//        }
//        List<User> friends = facebook.friendOperations().getFriendProfiles();
//        model.addAttribute("friends", friends);
//        return "friends";
//    }
//
//    private boolean getModel(Model model) {
//        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//            return true;
//        }
//        User profile = facebook.fetchObject("me", User.class, "id", "name", "link", "email");
//        model.addAttribute("userProfile", profile);
//        return false;
//    }
}
