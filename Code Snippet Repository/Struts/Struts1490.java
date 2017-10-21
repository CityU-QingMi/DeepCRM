    public void testCreateDefaultFileManager() throws Exception {
        // given
        fileManager = null;
        DefaultFileManagerFactory factory = new DefaultFileManagerFactory();
        factory.setFileManager(new DefaultFileManager());
        factory.setContainer(new DummyContainer());

        // when
        FileManager fm = factory.getFileManager();

        // then
        assertTrue(fm instanceof DefaultFileManager);
    }
