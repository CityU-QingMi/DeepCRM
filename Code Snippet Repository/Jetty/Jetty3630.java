    @Test
    public void testInvalidHostHeader() throws Exception
    {
        // Use a contextHandler with vhosts to force call to Request.getServerName()
        ContextHandler context = new ContextHandler();
        context.addVirtualHosts(new String[]{"something"});
        _server.stop();
        _server.setHandler(context);
        _server.start();


        // Request with illegal Host header
        String request="GET / HTTP/1.1\n"+
        "Host: whatever.com:xxxx\n"+
        "Content-Type: text/html;charset=utf8\n"+
        "Connection: close\n"+
        "\n";

        String responses=_connector.getResponse(request);
        assertThat(responses, Matchers.startsWith("HTTP/1.1 400"));
    }
