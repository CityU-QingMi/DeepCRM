    protected void doShutdown(Request baseRequest, HttpServletResponse response) throws IOException 
    {
        for (Connector connector : getServer().getConnectors()) 
        {
            connector.shutdown();
        }

        response.sendError(200, "Connectors closed, commencing full shutdown");
        baseRequest.setHandled(true);

        final Server server=getServer();
        new Thread()
        {
            @Override
            public void run ()
            {
                try
                {
                    shutdownServer(server);
                }
                catch (InterruptedException e)
                {
                    LOG.ignore(e);
                }
                catch (Exception e)
                {
                    throw new RuntimeException("Shutting down server",e);
                }
            }
        }.start();
    }
