    public void testIncludeWithWildcard() throws Exception {
        String configFile = "com/opensymphony/xwork2/config/providers/xwork-test-include-wildcard.xml";
        buildConfigurationProvider(configFile);

        Set<String> loadedFileNames = configuration.getLoadedFileNames();
        assertEquals(8, loadedFileNames.size());
        assertTrue(loadedFileNames.contains("com/opensymphony/xwork2/config/providers/xwork-include-after-package.xml"));
        assertTrue(loadedFileNames.contains("com/opensymphony/xwork2/config/providers/xwork-include-after-package-2.xml"));
        assertTrue(loadedFileNames.contains("com/opensymphony/xwork2/config/providers/xwork-include-before-package.xml"));
        assertTrue(loadedFileNames.contains("com/opensymphony/xwork2/config/providers/xwork-include-before-package-2.xml"));
        assertTrue(loadedFileNames.contains("com/opensymphony/xwork2/config/providers/xwork-include-parent.xml"));
        assertTrue(loadedFileNames.contains("com/opensymphony/xwork2/config/providers/xwork-test-include-wildcard.xml"));
        assertTrue(loadedFileNames.contains("xwork-test-beans.xml"));
        assertTrue(loadedFileNames.contains("xwork-test-default.xml"));
    }
