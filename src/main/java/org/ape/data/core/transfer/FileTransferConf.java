package org.ape.data.core.transfer;

/**
 * File Transfer Configuration Object
 * 
 * @author Conan_Z
 * @data 2013-1-23
 */
public class FileTransferConf {

    private String username;
    private String passowrd;
    private String ip;
    private int port;
    private int bufferSize = 1024;
    private String encoding = "utf-8";

    public FileTransferConf() {
    }

    public FileTransferConf(String username, String passowrd, String ip, int port) {
        this.username = username;
        this.passowrd = passowrd;
        this.ip = ip;
        this.port = port;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
