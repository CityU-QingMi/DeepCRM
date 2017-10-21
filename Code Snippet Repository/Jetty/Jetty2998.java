    protected void start() throws Exception
    {
        synchronized (this)
        {
            if (alive)
            {
                debug("Already started");
                return; // cannot start it again
            }
            ServerSocket serverSocket = listen();
            if (serverSocket != null)
            {
                alive = true;
                Thread thread = new Thread(new ShutdownMonitorRunnable(serverSocket));
                thread.setDaemon(true);
                thread.setName("ShutdownMonitor");
                thread.start();
            }
        }
    }
