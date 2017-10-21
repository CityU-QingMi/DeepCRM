    @Test
    public void test() throws IOException {
        final Path tempFile = Files.createTempFile("log4j2", ".xml");
        try {
            final Log4j1ConfigurationConverter.CommandLineArguments cla = new Log4j1ConfigurationConverter.CommandLineArguments();
            cla.setPathIn(pathIn);
            cla.setPathOut(tempFile);
            Log4j1ConfigurationConverter.run(cla);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
