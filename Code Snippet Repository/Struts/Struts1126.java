    public void testEmptySpaces() throws Exception {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork- test.xml";
        container.getInstance(FileManagerFactory.class).getFileManager().setReloadingConfigs(true);

        ConfigurationProvider provider = buildConfigurationProvider(filename);
        assertTrue(!provider.needsReload());

        URI uri = ClassLoaderUtil.getResource(filename, ConfigurationProvider.class).toURI();

        File file = new File(uri);

        assertTrue(file.exists());
        changeFileTime(file);

        assertTrue(provider.needsReload());
    }
