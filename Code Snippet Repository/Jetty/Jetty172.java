    public void start() throws Exception
    {
        System.out.println("Starting Ant Build ...");
        AntBuildProcess abp = new AntBuildProcess();
        _process = new Thread(abp);

        _process.start();
        
        abp.waitForStarted();
        
        // once this has returned we should have the connection info we need
        //_host = abp.getConnectionList().get(0)[0];
        //_port = Integer.parseInt(abp.getConnectionList().get(0)[1]);
        
    }
