    @Test
    public void testRoleRef() throws Exception
    {
        RoleCheckHandler check=new RoleCheckHandler();
        _security.setHandler(check);
        _security.setAuthenticator(new BasicAuthenticator());

        _server.start();

        String response;
        response = _connector.getResponse("GET /ctx/noauth/info HTTP/1.0\r\n\r\n", 100000, TimeUnit.MILLISECONDS);
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 200 OK"));

        response = _connector.getResponse("GET /ctx/auth/info HTTP/1.0\r\n" +
                "Authorization: Basic " + B64Code.encode("user2:password") + "\r\n" +
                "\r\n", 100000, TimeUnit.MILLISECONDS);
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 500 "));

        _server.stop();

        RoleRefHandler roleref = new RoleRefHandler();
        roleref.setHandler(_security.getHandler());
        _security.setHandler(roleref);
        roleref.setHandler(check);

        _server.start();

        response = _connector.getResponse("GET /ctx/auth/info HTTP/1.0\r\n" +
                "Authorization: Basic " + B64Code.encode("user2:password") + "\r\n" +
                "\r\n", 100000, TimeUnit.MILLISECONDS);
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 200 OK"));
    }
