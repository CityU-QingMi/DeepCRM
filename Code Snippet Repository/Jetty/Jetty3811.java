    @Test
    public void testDangerousSessionIdRemoval() throws Exception
    {  
        String expectedFilename =  "_0.0.0.0_dangerFile";    
        File targetFile = MavenTestingUtils.getTargetFile(expectedFilename);

        try
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

            //Create a file that is in the parent dir of the session storeDir

            targetFile.createNewFile();
            Assert.assertTrue("File should exist!", MavenTestingUtils.getTargetFile(expectedFilename).exists());

            //Verify that passing in a relative filename outside of the storedir does not lead
            //to deletion of file (needs deleteUnrecoverableFiles(true))
            Session session = handler.getSession("../_0.0.0.0_dangerFile");
            Assert.assertTrue(session == null);
            Assert.assertTrue("File should exist!", MavenTestingUtils.getTargetFile(expectedFilename).exists());
        }
        finally
        {
            if (targetFile.exists())
                IO.delete(targetFile);
        }
    }
