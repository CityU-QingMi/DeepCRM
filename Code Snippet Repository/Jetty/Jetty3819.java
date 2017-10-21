    @Test
    public void testBigResponse() throws Exception
    {
        server.setHandler(new HelloWorldHandler());
        server.start();

        SSLContext ctx=SSLContext.getInstance("TLS");
        ctx.init(null,SslContextFactory.TRUST_ALL_CERTS,new java.security.SecureRandom());

        int port=connector.getLocalPort();

        Socket client=ctx.getSocketFactory().createSocket("localhost",port);
        OutputStream os=client.getOutputStream();

        String request =
            "GET /?dump=102400 HTTP/1.1\r\n"+
            "Host: localhost:"+port+"\r\n"+
            "Connection: close\r\n"+
            "\r\n";

        os.write(request.getBytes());
        os.flush();

        String response = IO.toString(client.getInputStream());

        assertThat(response.length(),greaterThan(102400));
    }
