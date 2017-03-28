package services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.*;
import org.hibernate.SessionFactory;
import dao.*;
import model.*;

/*
* Login Service 
* valida credenciais e concede o token de acesso ao usuario
*/
public class LoginService{
	

	// verifica as credenciais do usuario e concede a token 
	public  static String Login(Request request,Response response){
                //HEADER
                 response.header("Access-Control-Allow-Origin", "*");
                 response.type("application/json");

                //get valores 
                 String email = request.queryParams("email");
                 String senha = request.queryParams("senha");
                
                //verificando se o parametro email foi enviado
                 if(email == null || email.isEmpty()){
                          response.status(400);//400 -> bad requested
                          JSONObject resposta = new JSONObject();
                          resposta.put("mensagem :", "o campo email deve ser informado");
                          return resposta.toJSONString();
                 }

                 //verificando de o parametro senha foi enviado
                 if(senha == null || senha.isEmpty()){
                          response.status(400);//400 -> bad requested
                          JSONObject resposta = new JSONObject();
                          resposta.put("mensagem :", "O campo senha  deve ser informado");
                          return resposta.toJSONString();
                 }

                JSONObject resposta = new JSONObject();
                UsuarioDAO userDAO = new UsuarioDAO();
                Usuario  user = userDAO.loadByEmail(email);
                
                if(email.equals(user.Getemail()) && senha.equals(user.Getsenha())){
                        //int emailint= Integer.parseInt(user.Getid().toString());
                        //String hex = Integer.toHexString(emailint);
                        String token = AuthHelper.createJsonWebToken(email,Long.parseLong("1"));
                        response.header("Token-Web", token);
                        resposta.put("user" ,user.toJSON());
                }else{
                	response.status(400);//bad request
                        if(user.Getemail()==null){

                	       resposta.put("mensagem :","Verifique seu Endereco de email");
                        }else{
                                resposta.put("mensagem :","Verifique sua senha");
                        }
                	return resposta.toJSONString();
                }

                return resposta.toJSONString();
        }

}