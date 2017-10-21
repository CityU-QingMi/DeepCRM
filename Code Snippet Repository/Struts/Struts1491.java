    public void testCreateDummyFileManager() throws Exception {
        // given
        fileManager = new DummyFileManager();
        DefaultFileManagerFactory factory = new DefaultFileManagerFactory();
        factory.setFileManager(new DefaultFileManager());
        factory.setContainer(new DummyContainer());

        // when
        FileManager fm = factory.getFileManager();

        // then
        assertTrue(fm instanceof DummyFileManager);
    }
