    public static Test suite() throws IOException, SQLException {

        TestSuite newSuite = new TestSuite();

        newSuite.addTest(new TestDbBackup("testSanity"));
        newSuite.addTest(new TestDbBackup("testBasicBackup"));
        newSuite.addTest(new TestDbBackup("testMainAlreadyOpen"));
        newSuite.addTest(new TestDbBackup("testGzip"));
        newSuite.addTest(new TestDbBackup("testOnlineBackup"));
        newSuite.addTest(new TestDbBackup("testTarFileNames"));
        newSuite.addTest(new TestDbBackup("testAutoNaming"));

        return newSuite;
    }
