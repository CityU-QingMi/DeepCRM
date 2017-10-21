    @Test
    public void testProviderBuffer() {
        final NoSqlAppender appender = NoSqlAppender.createAppender("anotherName02", null, null, "25", provider);

        assertNotNull("The appender should not be null.", appender);
        assertEquals("The toString value is not correct.",
                "anotherName02{ manager=noSqlManager{ description=anotherName02, bufferSize=25, provider=" + provider
                        + " } }", appender.toString());

        appender.stop();
    }
