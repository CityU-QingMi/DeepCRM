    @Test(timeout = 5000L)
    public void testStopLifecycle() throws Exception
    {
        // Set client timeout
        final int timeout = 1000;
        client.setMaxIdleTimeout(timeout);

        int clientCount = 3;
        CloseTrackingSocket clientSockets[] = new CloseTrackingSocket[clientCount];
        IBlockheadServerConnection serverConns[] = new IBlockheadServerConnection[clientCount];

        // Connect Multiple Clients
        for (int i = 0; i < clientCount; i++)
        {
            // Client Request Upgrade
            clientSockets[i] = new CloseTrackingSocket();
            Future<Session> clientConnectFuture = client.connect(clientSockets[i],server.getWsUri());

            // Server accepts connection
            serverConns[i] = server.accept();
            serverConns[i].upgrade();

            // client confirms connection via echo
            confirmConnection(clientSockets[i],clientConnectFuture,serverConns[i]);
        }

        // client lifecycle stop
        client.stop();

        // clients send close frames (code 1001, shutdown)
        for (int i = 0; i < clientCount; i++)
        {
            // server receives close frame
            confirmServerReceivedCloseFrame(serverConns[i],StatusCode.SHUTDOWN,containsString("Shutdown"));
        }

        // clients disconnect
        for (int i = 0; i < clientCount; i++)
        {
            clientSockets[i].assertReceivedCloseEvent(timeout,is(StatusCode.SHUTDOWN),containsString("Shutdown"));
        }
    }
