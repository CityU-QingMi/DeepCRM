    private void testProvider(String configFile) throws Exception {
        ConfigurationProvider provider = buildConfigurationProvider(configFile);
        assertTrue(!provider.needsReload());

        String fullPath = ClassLoaderUtil.getResource(configFile, ConfigurationProvider.class).toString();

        int startIndex = fullPath.indexOf(":file:/");
        int endIndex = fullPath.indexOf("!/");

        String jar = fullPath.substring(startIndex + (":file:/".length() - 1), endIndex).replaceAll("%20", " ");

        File file = new File(jar);

        assertTrue("File [" + file + "] doesn't exist!", file.exists());
        file.setLastModified(System.currentTimeMillis());

        assertTrue(!provider.needsReload());
    }
