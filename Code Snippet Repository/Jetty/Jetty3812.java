    @Test
    public void testUnrestorableFileRemoval() throws Exception
    {      
        Server server = new Server();
        SessionHandler handler = new SessionHandler();
        handler.setServer(server);
        final DefaultSessionIdManager idmgr = new DefaultSessionIdManager(server);
        idmgr.setServer(server);
        server.setSessionIdManager(idmgr);
      
        DefaultSessionCache ss = new DefaultSessionCache(handler);
        FileSessionDataStore ds = new FileSessionDataStore();
        ss.setSessionDataStore(ds);
        handler.setSessionCache(ss);
        ds.setDeleteUnrestorableFiles(true); //invalid file will be removed
        handler.setSessionIdManager(idmgr);
      
        File testDir = MavenTestingUtils.getTargetTestingDir("hashes");
        FS.ensureEmpty(testDir);

        ds.setStoreDir(testDir);
        handler.start();

        String expectedFilename = (System.currentTimeMillis()+ 10000)+"__0.0.0.0_validFile123";
        
        Assert.assertTrue(new File(testDir, expectedFilename).createNewFile());

        Assert.assertTrue("File should exist!", new File(testDir, expectedFilename).exists());

        Session session = handler.getSession("validFile123");

        Assert.assertTrue("File shouldn't exist!", !new File(testDir,expectedFilename).exists());
    }
