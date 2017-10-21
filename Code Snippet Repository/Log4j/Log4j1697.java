    @Test
    public void testLazyCreate() throws Exception {
        final Layout<String> layout = createPatternLayout();
        // @formatter:off
        final FileAppender appender = FileAppender.newBuilder()
            .withFileName(FILE_NAME)
            .withName("test")
            .withImmediateFlush(false)
            .withIgnoreExceptions(false)
            .withBufferedIo(false)
            .withBufferSize(1)
            .withLayout(layout)
            .withCreateOnDemand(createOnDemand)
            .build();
        // @formatter:on
        Assert.assertEquals(createOnDemand, appender.getManager().isCreateOnDemand());
        try {
            Assert.assertNotEquals(createOnDemand, Files.exists(PATH));
            appender.start();
            Assert.assertNotEquals(createOnDemand, Files.exists(PATH));
        } finally {
            appender.stop();
        }
        Assert.assertNotEquals(createOnDemand, Files.exists(PATH));
    }
