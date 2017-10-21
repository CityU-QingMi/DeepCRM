    public void testConfigsInJarFiles() throws Exception {
        container.getInstance(FileManagerFactory.class).getFileManager().setReloadingConfigs(true);
        testProvider("xwork-jar.xml");
        testProvider("xwork-zip.xml");
        testProvider("xwork - jar.xml");
        testProvider("xwork - zip.xml");

        testProvider("xwork-jar2.xml");
        testProvider("xwork-zip2.xml");
        testProvider("xwork - jar2.xml");
        testProvider("xwork - zip2.xml");
    }
