        public static void main(String[] args) throws Exception
        {
            QueuedThreadPool serverThreads = new QueuedThreadPool();
            serverThreads.setName("server");
            Server server = new Server(serverThreads);
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(8888);
            server.addConnector(connector);
            server.setHandler(new AbstractHandler()
            {
                @Override
                public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
                {
                    baseRequest.setHandled(true);
                    byte[] buffer = new byte[1024];
                    InputStream input = request.getInputStream();
                    while (true)
                    {
                        int read = input.read(buffer);
                        if (read < 0)
                            break;
                        long now = System.nanoTime();
                        long sleep = TimeUnit.MICROSECONDS.toNanos(1);
                        while (System.nanoTime() < now + sleep)
                        {
                            // Wait.
                        }
                    }
                }
            });
            server.start();
        }
