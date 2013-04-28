package org.ape.data.core.util;

import java.io.File;

import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

public class FtpServer {
	
	public void createFTPServer() throws FtpException{
		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory factory = new ListenerFactory();
		factory.setPort(2221);
		serverFactory.addListener("default", factory.createListener());
		org.apache.ftpserver.FtpServer server = serverFactory.createServer();         
		server.start();
	}
	
	public void createFTPSServer() throws FtpException{
		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory factory = new ListenerFactory();
		// set the port of the listener
		factory.setPort(2221);
		// define SSL configuration
		SslConfigurationFactory ssl = new SslConfigurationFactory();
		ssl.setKeystoreFile(new File("src/test/resources/ftpserver.jks"));
		ssl.setKeystorePassword("password");
		// set the SSL configuration for the listener
		factory.setSslConfiguration(ssl.createSslConfiguration());
		factory.setImplicitSsl(true);
		// replace the default listener
		serverFactory.addListener("default", factory.createListener());
		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		userManagerFactory.setFile(new File("myusers.properties"));
		serverFactory.setUserManager(userManagerFactory.createUserManager());
		// start the server
		org.apache.ftpserver.FtpServer server = serverFactory.createServer(); 
		server.start();
	}
	
	public static void main(String[] args) throws FtpException {
		FtpServer fs = new FtpServer();
		fs.createFTPServer();
	}

}
