    @Test
    public void testViaServletUpgradeRequest() throws Exception
    {
        // Setup client
        HttpCookie cookie = new HttpCookie("hello","world");
        cookie.setPath("/");
        cookie.setMaxAge(100000);
        
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        request.setCookies(Collections.singletonList(cookie));

        // Client connects
        CookieTrackingSocket clientSocket = new CookieTrackingSocket();
        Future<Session> clientConnectFuture = client.connect(clientSocket,server.getWsUri(),request);

        // Server accepts connect
        IBlockheadServerConnection serverConn = server.accept();

        // client confirms upgrade and receipt of frame
        String serverCookies = confirmClientUpgradeAndCookies(clientSocket,clientConnectFuture,serverConn);

        Assert.assertThat("Cookies seen at server side",serverCookies,containsString("hello=world"));
    }
