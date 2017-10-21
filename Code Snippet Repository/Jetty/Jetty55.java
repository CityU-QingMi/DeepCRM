    @Override
    public void configure(SSLEngine sslEngine, Connection connection)
    {
        ALPNClientConnection alpn = (ALPNClientConnection)connection;
        SSLParameters sslParameters = sslEngine.getSSLParameters();
        List<String> protocols = alpn.getProtocols();
        sslParameters.setApplicationProtocols(protocols.toArray(new String[protocols.size()]));
        sslEngine.setSSLParameters(sslParameters);
        ((DecryptedEndPoint)connection.getEndPoint()).getSslConnection()
                .addHandshakeListener(new ALPNListener(alpn));
    }
