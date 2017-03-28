package services;

import spark.*;
import org.json.simple.JSONObject;

public class UtilService{

	public static  void InsertHeader(Response response){
		 //HEADER
          response.header("Access-Control-Allow-Origin", "*");
          response.type("application/json");

	}

	public static  boolean CheckToken(Request request , Response response)throws Exception{
		//verifica o token de autenticacao do usuario 
        String token = request.headers("Web-Token");
        if(token == null ){
               throw new Exception("errro ao validar");

        }

		TokenInfo info = null ;
		
		info = AuthHelper.verifyToken(token);
		
		if(info == null ){
                        return false;
        }

        return true;
	}
}