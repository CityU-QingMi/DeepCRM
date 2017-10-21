    @Test
    public void testFilePermissions() throws Exception {
        final Logger logger = loggerContextRule.getLogger(FileAppenderPermissionsTest.class);
        for (int i = 0; i < 1000; ++i) {
            final String message = "This is test message number " + i;
            logger.debug(message);
        }
        assertEquals("rw-------", PosixFilePermissions.toString(
                    Files.getPosixFilePermissions(Paths.get("target/permissions1/AppenderTest-1.log"))));
    }
