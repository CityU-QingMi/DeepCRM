    @Override
    protected Connection newConnection(SelectableChannel channel, EndPoint endpoint)
    {
        SSLEngine engine = __sslCtxFactory.newSSLEngine();
        engine.setUseClientMode(false);
        SslConnection sslConnection = new SslConnection(__byteBufferPool, _threadPool, endpoint, engine);
        sslConnection.setRenegotiationAllowed(__sslCtxFactory.isRenegotiationAllowed());
        sslConnection.setRenegotiationLimit(__sslCtxFactory.getRenegotiationLimit());
        Connection appConnection = super.newConnection(channel,sslConnection.getDecryptedEndPoint());
        sslConnection.getDecryptedEndPoint().setConnection(appConnection);
        return sslConnection;
    }
