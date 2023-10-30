package com.etiya.campus.aj.db;

import com.etiya.campus.aj.ff.FileHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {
    private String ortam;
    private String host;
    private String port;
    private String serviceName;
    private String user;
    private String password;
    private String prefix;
    private String connectionMethod;
    private String driverPath;
    private String tnsName;
    private Connection connection;
    private Connection localConnection;
    public Connection getConnection(boolean connect) {
        if((this.connection == null)&&connect)
            this.connect();
        return this.connection;
    }

    public boolean init(String fileName){
        Map<String, String> result = null;
        FileHandler fileHandler = new FileHandler(fileName);
        String currentLine = null;
        if (fileHandler != null) {
            result = new HashMap<String,String>();
            while(fileHandler.hasNextLine()){
                currentLine = fileHandler.getLine();
                String[] kv = currentLine.split("=");
                String key = kv[0];
                String value = kv[1];
                if ((key != null) && (!key.equals("")) && (value != null) && (!value.equals(""))) {
                    result.put(key, value);
                }
            }
        }
        if(result!=null){
            return this.init(result);
        }
        else
            return false;
    }
    public boolean init(Map<String,String> info){
        boolean result = false;
        if(info!=null){
            this.host = info.get("databaseHost");
            this.port = info.get("databasePort");
            this.serviceName = info.get("databaseServiceName");
            this.user = info.get("databaseUser");
            this.password = info.get("databasePassword");
            this.ortam = info.get("ortam");
            this.prefix = info.get("tableprefix");
            this.connectionMethod = info.get("connectionMethod");
            this.driverPath = info.get("driverPath");
            this.tnsName = info.get("tnsName");
        }
        return result;
    }

    private void connect(){
        if ((this.host != null) && (!this.host.equals("")) && (this.port != null) && (!this.port.equals(""))
                && (this.serviceName != null) && (!this.serviceName.equals("")) && (this.user != null)
                && (!this.user.equals("")) && (this.password != null) && (!this.password.equals(""))) {
            try{
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                }
                catch(ClassNotFoundException ex) {
                    System.out.println("Error: unable to load driver class!");
                    System.exit(1);
                }
                if(this.connectionMethod!=null) {
                    if(this.connectionMethod.equals("TNS")) {
                        System.out.println("TNS");
                        System.setProperty("oracle.net.tns_admin",
                                this.driverPath);
                        System.out.println("jdbc:oracle:thin:@"+this.tnsName+" - "+this.user+" - "+ this.password);
                        System.out.println(this.user);
                        this.connection = DriverManager.getConnection("jdbc:oracle:thin:@"+this.tnsName, this.user, this.password);

                    }
                    else
                    if(this.connectionMethod.equals("URL")) {
                        System.out.println("jdbc:oracle:thin:@"+this.host+":"+this.port+"/"+this.serviceName+" - "+this.user+" - "+ this.password);
                        this.connection = DriverManager.getConnection("jdbc:oracle:thin:@"+this.host+":"+this.port+":"+this.serviceName, this.user, this.password);
                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

		/*if(this.ortam != null && this.localConnection == null){
			try{
				try {
					   Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
					}
					catch(ClassNotFoundException ex) {
					   System.out.println("Error: unable to load driver class!");
					}
				Driver derbyEmbeddedDriver = new EmbeddedDriver();
				DriverManager.registerDriver(derbyEmbeddedDriver);
				//this.localConnection = DriverManager.getConnection("jdbc:derby:"+this.ortam+"_db;create=true");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}*/
    }

    public boolean closeConnection(){
        boolean result = false;
        try{
            if(this.connection!=null)
                this.connection.close();
            this.connection = null;
            if(this.localConnection!=null)
                this.localConnection.close();
            this.localConnection = null;
            result = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean isConnectionAvailable(){
        return this.connection!=null;
    }

    public String getTablePrefix(){
        return this.prefix;
    }
}
