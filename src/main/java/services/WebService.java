
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WebService{

	/*static String GetUser(){ 

                
        	JSONObject obj = new JSONObject();

                obj.put("nome", "Benedito da silva");
                obj.put("idade", new Integer(100));

                obj.put("name", "mario");
                obj.put("age", new Integer(100));
                JSONArray contas = new JSONArray();
                JSONArray conta = new JSONArray();

                conta.add("valor : 250,00");
                conta.add("vencimento : 25/12/2011");
                contas.add(conta);

                contas.add("outra conta");
                contas.add("mais uma conta");

                obj.put("contas",contas);
               
        	return obj.toJSONString().toString();

	}
        */

        static String Login(String email , String senha){
                JSONObject obj = new JSONObject();
                obj.put("erro",false);
                obj.put("msg ","ok");

                JSONArray usuario = new JSONArray();
                usuario.add("email :"+email);
                usuario.add("nome : Francisco");
                usuario.add("UID  : 776DGEHS9384AASD7");

                obj.put("usuario",usuario);


                return obj.toJSONString().toString();
        }

        
}
