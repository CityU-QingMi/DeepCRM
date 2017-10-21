    @Test
    public void testViaCookieManager() throws Exception
    {
        // Setup client
        CookieManager cookieMgr = new CookieManager();
        client.setCookieStore(cookieMgr.getCookieStore());
        HttpCookie cookie = new HttpCookie("hello","world");
        cookie.setPath("/");
        cookie.setVersion(0);
        cookie.setMaxAge(100000);
        cookieMgr.getCookieStore().add(server.getWsUri(),cookie);
        
        cookie = new HttpCookie("foo","bar is the word");
        cookie.setPath("/");
        cookie.setMaxAge(100000);
        cookieMgr.getCookieStore().add(server.getWsUri(),cookie);

        // Client connects
        CookieTrackingSocket clientSocket = new CookieTrackingSocket();
        Future<Session> clientConnectFuture = client.connect(clientSocket,server.getWsUri());

        // Server accepts connect
        IBlockheadServerConnection serverConn = server.accept();

        // client confirms upgrade and receipt of frame
        String serverCookies = confirmClientUpgradeAndCookies(clientSocket,clientConnectFuture,serverConn);

        assertThat("Cookies seen at server side",serverCookies,containsString("hello=world"));
        assertThat("Cookies seen at server side",serverCookies,containsString("foo=bar is the word"));
    }
