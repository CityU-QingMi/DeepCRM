    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket)
    {
        SSLSocket sslSocket = (SSLSocket)socket;

        String alias = chooseServerAlias(keyType,issuers,sslSocket.getSSLParameters().getSNIMatchers(),sslSocket.getHandshakeSession());
        if (alias==NO_MATCHERS)
            alias=_delegate.chooseServerAlias(keyType,issuers,socket);
        if (LOG.isDebugEnabled())
            LOG.debug("Chose alias {}/{} on {}",alias,keyType,socket);
        return alias;
    }
