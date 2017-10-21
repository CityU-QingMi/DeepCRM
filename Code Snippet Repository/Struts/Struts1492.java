    public void testFileManagerFactoryWithRealConfig() throws Exception {
        // given
        DefaultFileManagerFactory factory = new DefaultFileManagerFactory();
        container.inject(factory);

        // when
        FileManager fm = factory.getFileManager();

        // then
        assertTrue(fm instanceof DefaultFileManager);
    }
