package com.github.ungarscool1.Roboto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.javacord.api.entity.server.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ServerLanguage {
	
	private BufferedReader reader = null;
	private boolean init = false;
	private Gson gson = new Gson();
	
	public ServerLanguage() {
		try {
            reader = new BufferedReader(new FileReader("serversLanguage.json"));
    		gson = new GsonBuilder().setPrettyPrinting().create();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("The file can't be readable / writable / do not exist");
        }
	}

	public void setServerLanguage(Server server, String lang) {
		JsonObject object = gson.fromJson(reader,JsonObject.class);
		try {
			object.remove(server.getIdAsString());
		} catch (Exception e) {
			System.err.println("Le serveur " + server.getName() + " n'est pas répertorié... Ajout");
		}
        object.addProperty(server.getIdAsString(), lang);
    }
    
    public String getServerLanguage(Server server) {
    	JsonObject object = gson.fromJson(reader,JsonObject.class);
    	String lang;
    	try {
    		lang = object.get(server.getIdAsString()).getAsString();
    	} catch (NullPointerException e) {
    		setServerLanguage(server, "en_US");
    		e.printStackTrace();
    		return "en_US";
    	}
    	return lang;
    }
	
}
