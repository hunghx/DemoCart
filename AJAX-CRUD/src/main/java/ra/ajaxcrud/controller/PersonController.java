package ra.ajaxcrud.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.ajaxcrud.model.Persons;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/person")
public class PersonController {
    private static final Gson GSON = new GsonBuilder().create();
  @RequestMapping ("/api/findById/{id}")
    public void findById(HttpServletResponse response, @PathVariable("id") Long id){
      // gọi qua database để lấy dữ liệu
      Persons persons = new Persons(1, "hunghx", true);
      String data = GSON.toJson(persons);
      response.setHeader("Content-Type","application/json");
      response.setStatus(200);
      PrintWriter out=null;
      try {
          out = response.getWriter();
          out.write(data);
          out.flush();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }finally {
          out.close();
      }

  }

}
