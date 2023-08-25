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
import ra.academy.model.Product;
import ra.academy.model.User;
import ra.academy.service.impl.UserService;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String getList(Model model,HttpSession session){
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"Sản phẩm 1","mô tả",100,10,"https://images.pexels.com/photos/821651/pexels-photo-821651.jpeg?auto=compress&cs=tinysrgb&w=600"));
        list.add(new Product(2,"Sản phẩm 2","mô tả",100,10,"https://images.pexels.com/photos/821651/pexels-photo-821651.jpeg?auto=compress&cs=tinysrgb&w=600"));
        list.add(new Product(3,"Sản phẩm 3","mô tả",100,10,"https://images.pexels.com/photos/821651/pexels-photo-821651.jpeg?auto=compress&cs=tinysrgb&w=600"));
        list.add(new Product(4,"Sản phẩm 4","mô tả",100,10,"https://images.pexels.com/photos/821651/pexels-photo-821651.jpeg?auto=compress&cs=tinysrgb&w=600"));
        list.add(new Product(5,"Sản phẩm 5","mô tả",100,10,"https://images.pexels.com/photos/821651/pexels-photo-821651.jpeg?auto=compress&cs=tinysrgb&w=600"));
        model.addAttribute("list",list);
        session.setAttribute("products",list);
        session.setAttribute("cart",new ArrayList<>());
        return "index";
    }
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
        // đăng nhập thành công
        session.setAttribute("userlogin",new User());
        session.setAttribute("cart",new ArrayList<>());
        return "home";
    }

}
