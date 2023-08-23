package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.academy.dto.request.FormLoginDto;
import ra.academy.model.User;
import ra.academy.service.impl.UserService;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public String handleLogin( @ModelAttribute("login_form") FormLoginDto formLoginDto, HttpSession session, BindingResult errors){
        // checkk validate
        formLoginDto.checkValidate(errors,userService);
        // kiểm tra bindingresult có nhận lỗi nào không

        if(errors.hasErrors()){
            return "login";
        }

        return "home";
    }

}
