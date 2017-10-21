    @Test
    public void testIrregularFilenames() throws Exception
    {
        Server server = new Server();
        SessionHandler handler = new SessionHandler();
        handler.setServer(server);
        final DefaultSessionIdManager idmgr = new DefaultSessionIdManager(server);
        idmgr.setServer(server);
        server.setSessionIdManager(idmgr);

        FileSessionDataStore ds = new FileSessionDataStore();
        ds.setDeleteUnrestorableFiles(true);
        DefaultSessionCache ss = new DefaultSessionCache(handler);
        handler.setSessionCache(ss);
        ss.setSessionDataStore(ds);
        File testDir = MavenTestingUtils.getTargetTestingDir("hashes");
        FS.ensureEmpty(testDir);
        ds.setStoreDir(testDir);
        handler.setSessionIdManager(idmgr);
        handler.start();

        //Create a file in the session storeDir that has no underscore.
        File noUnderscore = new File(testDir, "spuriousFile");
        noUnderscore.createNewFile();
        try
        {
            Assert.assertTrue("Expired should be empty!", ds.getExpired(Collections.emptySet()).isEmpty());
        }
        finally
        {
            noUnderscore.delete();
        }

        //Create a file that starts with a non-number before an underscore
        File nonNumber = new File(testDir, "nonNumber_0.0.0.0_spuriousFile");
        nonNumber.createNewFile();
        try
        {
            Assert.assertTrue("Expired should be empty!", ds.getExpired(Collections.emptySet()).isEmpty());
        }
        finally
        {
            nonNumber.delete();
        }

    }
