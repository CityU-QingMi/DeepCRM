    @Override
    public void configure(SSLEngine sslEngine,Connection connection)
    {
        try
        {
            Method method = sslEngine.getClass().getMethod("setHandshakeApplicationProtocolSelector", BiFunction.class);
            method.setAccessible(true);
            method.invoke(sslEngine,new ALPNCallback((ALPNServerConnection)connection));
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
