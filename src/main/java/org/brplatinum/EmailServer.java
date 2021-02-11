package org.brplatinum;

public class EmailServer {
    private String hostname;
    private String username;
    private String password;
    private int port;
    private Encryption encryption;

    public EmailServer() {
        hostname = null;
        username = null;
        password = null;
        port = 1;
        encryption = Encryption.TLS;
    }

    public EmailServer(String hostname, String username, String password, int port, Encryption encryption) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = port;
        this.encryption = encryption;
    }

    public EmailServer(String hostname, String username, String password, String port, Encryption encryption) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        if (port.isEmpty()) {
            this.port = 0;
        } else {
            this.port = Integer.parseInt(port);
        }
        this.encryption = encryption;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }
}
