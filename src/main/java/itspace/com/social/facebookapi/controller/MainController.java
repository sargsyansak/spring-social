package itspace.com.social.facebookapi.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
}
