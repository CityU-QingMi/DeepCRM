    protected void assumeCanConnectTo(String host, int port)
    {
        try
        {
            new Socket(host, port).close();
        }
        catch (Throwable x)
        {
            Assume.assumeNoException(x);
        }
    }
