    public static int findFreePort(String systemProperty)
    {
        String freeport = System.getProperty(systemProperty);
        if (freeport!=null)
            return Integer.valueOf(freeport);
        
        try (ServerSocket socket = new ServerSocket(0))
        {
            socket.setReuseAddress(true);
            int port = socket.getLocalPort();
            System.setProperty(systemProperty,Integer.toString(port));
            return port;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
