package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.academy.dto.request.FormLoginDto;
import ra.academy.model.User;
import ra.academy.service.impl.UserService;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/form-login")
    public ModelAndView login(){
        return new ModelAndView("login","login_form",new FormLoginDto());
    }
    @PostMapping("/handle-login")
    public String handleLogin(HttpSession session, @ModelAttribute("login_form") FormLoginDto formLoginDto){
        // checkk validate
        // tao mois user
        User user = userService.login(formLoginDto);
        if(user == null){
            // looix
            return "redirect:/form-login";
        }
        session.setAttribute("userlogin",user);
        return "home";
    }

}
