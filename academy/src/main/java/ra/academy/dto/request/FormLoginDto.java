package ra.academy.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import ra.academy.service.impl.UserService;

@NoArgsConstructor
@AllArgsConstructor
public class FormLoginDto {

    private final  String PATTERN_PASS = "/^[a-z]{6,}$/";
    private String username;
    private String password;

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void checkValidate(Errors errors,UserService userService){

        // kiểm tra trường username có để trống hay không
        if(this.username.trim().equals("")){
            errors.rejectValue("username","username.empty");
        }else if(this.password.length()<8){
            errors.rejectValue("password","password.invalid");
        }else if(userService.login(this)==null){
            errors.rejectValue("password","account.invalid");
        }
    }
}
