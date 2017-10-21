    public static void setUpServer(TestableJettyServer testableserver, Class<?> testclazz) throws Exception
    {
        File testWorkDir = MavenTestingUtils.getTargetTestingDir(testclazz.getName());
        FS.ensureDirExists(testWorkDir);

        System.setProperty("java.io.tmpdir",testWorkDir.getAbsolutePath());

        server = testableserver;
        server.load();
        server.start();
        //server.getServer().dumpStdErr();
    }
