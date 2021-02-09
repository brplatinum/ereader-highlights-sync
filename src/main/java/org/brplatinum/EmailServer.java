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
}
