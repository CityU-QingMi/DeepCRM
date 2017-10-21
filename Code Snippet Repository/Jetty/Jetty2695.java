    @Test
    public void testDeferredBasic() throws Exception
    {
        _security.setAuthenticator(new BasicAuthenticator());
        _server.start();

        String response;

        response = _connector.getResponse("GET /ctx/noauth/info HTTP/1.0\r\n"+
            "\r\n");
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 200 OK"));
        Assert.assertThat(response, Matchers.containsString("user=null"));

        response = _connector.getResponse("GET /ctx/noauth/info HTTP/1.0\r\n"+
                "Authorization: Basic " + B64Code.encode("admin:wrong") + "\r\n" +
            "\r\n");
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 200 OK"));
        Assert.assertThat(response, Matchers.containsString("user=null"));

        response = _connector.getResponse("GET /ctx/noauth/info HTTP/1.0\r\n"+
                "Authorization: Basic " + B64Code.encode("admin:password") + "\r\n" +
            "\r\n");
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 200 OK"));
        Assert.assertThat(response, Matchers.containsString("user=admin"));
    }
