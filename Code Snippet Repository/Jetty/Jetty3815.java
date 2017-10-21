    @Test
    public void testClose() throws Exception
    {
        File keystore = MavenTestingUtils.getTestResourceFile("keystore");
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStoreResource(Resource.newResource(keystore));
        sslContextFactory.setKeyStorePassword("storepwd");
        sslContextFactory.setKeyManagerPassword("keypwd");

        Server server=new Server();
        ServerConnector connector=new ServerConnector(server, sslContextFactory);
        connector.setPort(0);

        server.addConnector(connector);
        server.setHandler(new WriteHandler());
        server.start();
        
        SSLContext ctx=SSLContext.getInstance("TLSv1.2");
        ctx.init(null,SslContextFactory.TRUST_ALL_CERTS,new java.security.SecureRandom());

        int port=connector.getLocalPort();

        Socket socket=ctx.getSocketFactory().createSocket("localhost",port);
        OutputStream os=socket.getOutputStream();

        os.write((
            "GET /test HTTP/1.1\r\n"+
            "Host:test\r\n"+
            "Connection:close\r\n\r\n").getBytes());
        os.flush();

        BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line;
        while ((line=in.readLine())!=null)
        {
            if (line.trim().length()==0)
                break;
        }

        Thread.sleep(2000);

        while (in.readLine()!=null)
            Thread.yield();
    }
