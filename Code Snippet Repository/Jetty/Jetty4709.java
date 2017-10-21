    public static void main (String... args) throws Exception
    {
        Server server = new Server();
        
        HttpConnectionFactory http = new HttpConnectionFactory();
        ProxyConnectionFactory proxy = new ProxyConnectionFactory(http.getProtocol());
        UnixSocketConnector connector = new UnixSocketConnector(server,proxy,http);
        server.addConnector(connector);
        
        Path socket = Paths.get(connector.getUnixSocket());
        if (Files.exists(socket))
            Files.delete(socket);
        
        server.setHandler(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            protected void doNonErrorHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
            {
                int l = 0;
                if (request.getContentLength()!=0)
                {
                    InputStream in = request.getInputStream();
                    byte[] buffer = new byte[4096];
                    int r = 0;
                    while (r>=0)
                    {
                        l += r;
                        r = in.read(buffer);
                    }
                }
                baseRequest.setHandled(true);
                response.setStatus(200);
                response.getWriter().write("Hello World "+new Date() + "\r\n");
                response.getWriter().write("remote="+request.getRemoteAddr()+":"+request.getRemotePort()+"\r\n");
                response.getWriter().write("local ="+request.getLocalAddr()+":"+request.getLocalPort()+"\r\n");
                response.getWriter().write("read ="+l+"\r\n");
            }
        });
        
        server.start();
        
        while (true)
        {
            Thread.sleep(5000);
            connector.dumpStdErr();
        }
        
        // server.join();
    }
