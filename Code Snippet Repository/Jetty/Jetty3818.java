    @Test
    public void testHelloWorld() throws Exception
    {
        server.setHandler(new HelloWorldHandler());
        server.start();

        SSLContext ctx=SSLContext.getInstance("TLS");
        ctx.init(null,SslContextFactory.TRUST_ALL_CERTS,new java.security.SecureRandom());

        int port=connector.getLocalPort();

        Socket client=ctx.getSocketFactory().createSocket("localhost",port);
        OutputStream os=client.getOutputStream();

        String request =
            "GET / HTTP/1.1\r\n"+
            "Host: localhost:"+port+"\r\n"+
            "Connection: close\r\n"+
            "\r\n";

        os.write(request.getBytes());
        os.flush();

        String response = IO.toString(client.getInputStream());

        assertThat(response, Matchers.containsString("200 OK"));
        assertThat(response,Matchers.containsString(HELLO_WORLD));
    }
