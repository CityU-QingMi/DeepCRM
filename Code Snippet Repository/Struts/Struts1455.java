    public void testReloadingConfigs() throws Exception {
        // given
        container.getInstance(FileManagerFactory.class).setReloadingConfigs("false");
        FileManager fm = container.getInstance(FileManagerFactory.class).getFileManager();
        String resourceName = "xwork-sample.xml";
        assertFalse(fm.fileNeedsReloading(resourceName));

        // when
        container.getInstance(FileManagerFactory.class).setReloadingConfigs("true");

        // then
        fm = container.getInstance(FileManagerFactory.class).getFileManager();
        assertTrue(fm.fileNeedsReloading(resourceName));
    }
