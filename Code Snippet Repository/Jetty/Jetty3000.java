    private ServerSocket listen()
    {
        int port = getPort();
        if (port < 0)
        {
            debug("Not enabled (port < 0): %d", port);
            return null;
        }

        String key = getKey();
        try
        {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName(host), port));
            if (port == 0)
            {
                port = serverSocket.getLocalPort();
                System.out.printf("STOP.PORT=%d%n", port);
                setPort(port);
            }

            if (key == null)
            {
                key = Long.toString((long)(Long.MAX_VALUE * Math.random() + this.hashCode() + System.currentTimeMillis()), 36);
                System.out.printf("STOP.KEY=%s%n", key);
                setKey(key);
            }

            return serverSocket;
        }
        catch (Throwable x)
        {
            debug(x);
            System.err.println("Error binding ShutdownMonitor to port " + port + ": " + x.toString());
            return null;
        }
        finally
        {
            // establish the port and key that are in use
            debug("STOP.PORT=%d", port);
            debug("STOP.KEY=%s", key);
        }
    }
