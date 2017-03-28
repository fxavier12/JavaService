
import spark.ModelAndView;
import org.json.simple.JSONObject;
import services.LoginService;
import services.UserService;
import static spark.Spark.*;
/*Web service Java - Maven 
*
*  tabela de codigos http --> http://www.restapitutorial.com/httpstatuscodes.html
*
*/
public class Main {

  public static void main(String[] args) {
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");
     

    get("/user/:id", (request, response) ->{
       return UserService.GetUser(request,response);
    });

    post("/user", (request, response) ->{
          return UserService.NewUser(request,response);
    });

    post("/login", (request, response) ->{
         return LoginService.Login(request,response);
    });
    
  }
}

