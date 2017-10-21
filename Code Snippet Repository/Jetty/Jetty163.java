    public void start()
    {
        try
        {
            configure();
            
            configureWebApps();
            
            server.start();
         
            System.setProperty("jetty.ant.server.port","" + ((ServerConnector)server.getConnectors()[0]).getLocalPort());
            
            String host = ((ServerConnector)server.getConnectors()[0]).getHost();
            
            if (host == null)
            {
                System.setProperty("jetty.ant.server.host", "localhost");
            }
            else
            {
                System.setProperty("jetty.ant.server.host", host);
            }
            
            startScanners();
            
            TaskLog.log("Jetty AntTask Started");

            if (!daemon)
                server.join();
        }
        catch (InterruptedException e)
        {
            new RuntimeException(e);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            new RuntimeException(e);
        }
    }
