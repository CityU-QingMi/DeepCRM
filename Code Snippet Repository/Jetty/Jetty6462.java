    private String confirmClientUpgradeAndCookies(CookieTrackingSocket clientSocket, Future<Session> clientConnectFuture, IBlockheadServerConnection serverConn)
            throws Exception
    {
        // Server upgrades
        List<String> upgradeRequestLines = serverConn.upgrade();
        List<String> upgradeRequestCookies = serverConn.regexFind(upgradeRequestLines,"^Cookie: (.*)$");

        // Server responds with cookies it knows about
        TextFrame serverCookieFrame = new TextFrame();
        serverCookieFrame.setFin(true);
        serverCookieFrame.setPayload(QuoteUtil.join(upgradeRequestCookies,","));
        serverConn.write(serverCookieFrame);
        serverConn.flush();

        // Confirm client connect on future
        clientConnectFuture.get(10,TimeUnit.SECONDS);
        clientSocket.awaitOpen(2,TimeUnit.SECONDS);
    
        try
        {
            // Wait for client receipt of cookie frame via client websocket
            clientSocket.messageQueue.awaitEventCount(1, 3, TimeUnit.SECONDS);
        }
        catch (TimeoutException e)
        {
            e.printStackTrace(System.err);
            assertThat("Message Count", clientSocket.messageQueue.size(), is(1));
        }

        String cookies = clientSocket.messageQueue.poll();
        LOG.debug("Cookies seen at server: {}",cookies);
        
        // Server closes connection
        serverConn.close(StatusCode.NORMAL);
    
        return cookies;
    }
