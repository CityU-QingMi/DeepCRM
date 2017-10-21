    @Test
    public void testDataRedirection() throws Exception
    {
        _security.setAuthenticator(new BasicAuthenticator());
        _server.start();

        String response;

        response = _connector.getResponse("GET /ctx/data/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 403"));
        
        _config.setSecurePort(8443);
        _config.setSecureScheme("https");

        response = _connector.getResponse("GET /ctx/data/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 302 Found"));
        Assert.assertTrue(response.indexOf("Location") > 0);
        Assert.assertTrue(response.indexOf(":8443/ctx/data/info") > 0);
        Assert.assertThat(response,Matchers.not(Matchers.containsString("https:///")));
        
        _config.setSecurePort(443);
        response = _connector.getResponse("GET /ctx/data/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 302 Found"));
        Assert.assertTrue(response.indexOf("Location") > 0);
        Assert.assertTrue(!response.contains(":443/ctx/data/info"));

        _config.setSecurePort(8443);
        response = _connector.getResponse("GET /ctx/data/info HTTP/1.0\r\nHost: wobble.com\r\n\r\n");
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 302 Found"));
        Assert.assertTrue(response.indexOf("Location") > 0);
        Assert.assertTrue(response.indexOf("https://wobble.com:8443/ctx/data/info") > 0);

        _config.setSecurePort(443);
        response = _connector.getResponse("GET /ctx/data/info HTTP/1.0\r\nHost: wobble.com\r\n\r\n");
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 302 Found"));
        Assert.assertTrue(response.indexOf("Location") > 0);
        Assert.assertTrue(!response.contains(":443"));
        Assert.assertTrue(response.indexOf("https://wobble.com/ctx/data/info") > 0);
    }
