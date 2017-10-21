    @Override
    public void configure(SSLEngine sslEngine, Connection connection)
    {
        try
        {
            Method setAlpnProtocols = sslEngine.getClass().getDeclaredMethod("setApplicationProtocols", String[].class);
            setAlpnProtocols.setAccessible(true);
            ALPNClientConnection alpn = (ALPNClientConnection)connection;
            String[] protocols = alpn.getProtocols().toArray(new String[0]);
            setAlpnProtocols.invoke(sslEngine, (Object)protocols);
            ((SslConnection.DecryptedEndPoint)connection.getEndPoint()).getSslConnection()
                    .addHandshakeListener(new ALPNListener(alpn));
        }
        catch (RuntimeException x)
        {
            throw x;
        }
        catch (Exception x)
        {
            throw new RuntimeException(x);
        }
    }
