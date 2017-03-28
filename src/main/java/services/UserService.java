package services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.*;
import org.hibernate.*;
import dao.UsuarioDAO;
import model.*;


/*
* User Service 
* /user
*/
public class UserService{
	

	// busca um usuario pelo id
	 public static String GetUser(Request request,Response response ){
                 UtilService.InsertHeader(response);//adiciona o header 



                try{
                   UtilService.CheckToken(request,response);
                } catch(Exception err){
                        response.status(401);
                        JSONObject resposta = new JSONObject();
                        resposta.put("mensagem :", "Token invalido");
                        return resposta.toJSONString();
                }
                
                 
                //get valores 
                 String id = request.params(":id");

                
                //verificando se o parametro id foi enviado
                 if(id == null || id.isEmpty()){
                          response.status(400);//400 -> bad requested
                          JSONObject resposta = new JSONObject();
                          resposta.put("mensagem :", "o campo id deve ser informado");
                          return resposta.toJSONString();
                }

                 

                JSONObject resposta = new JSONObject();
                UsuarioDAO userDAO = new UsuarioDAO();
                Usuario user = new Usuario();
                try{
                        user   = userDAO.load(id);
                        return user.toJSON().toJSONString();
                }catch(Exception err){
                                //not working
                }
                //nao encontrou
                response.status(404);
                resposta.put("msg","Usuario nao encontrado");
                return resposta.toJSONString();
                
        }

        //cria um novo usuario 
        public static String NewUser(Request request ,Response response){
                //HEADER
                 response.header("Access-Control-Allow-Origin", "*");
                 response.type("application/json");

                //get valores 
                 String email = request.queryParams("email");
                 String senha = request.queryParams("senha");
                 String nome = request.queryParams("nome");

                
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

                  //verificando de o parametro nome foi enviado
                 if(nome == null || nome.isEmpty()){
                          response.status(400);//400 -> bad requested
                          JSONObject resposta = new JSONObject();
                          resposta.put("mensagem :", "O campo senha  deve ser informado");
                          return resposta.toJSONString();
                 }


                Usuario user =new Usuario(nome,email,senha);
                UsuarioDAO userDAO = new UsuarioDAO();

                try{
                         userDAO.save(user);
                 }catch(Exception ex){
                        System.out.println("Ocorreu um erro ao salvar");
                 }
               

                return user.toJSON().toJSONString();
        }

}