    public void testNeedsReload() throws Exception {
        container.getInstance(FileManagerFactory.class).setReloadingConfigs("true");
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-actions.xml";
        ConfigurationProvider provider = buildConfigurationProvider(filename);
        container.getInstance(FileManagerFactory.class).setReloadingConfigs("true");

        assertTrue(!provider.needsReload()); // Revision exists and timestamp didn't change

        File file = new File(getClass().getResource("/" + filename).toURI());
        assertTrue("not exists: " + file.toString(), file.exists());
        changeFileTime(file);

        assertTrue(provider.needsReload());
    }
