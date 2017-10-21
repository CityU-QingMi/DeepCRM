    @Test
    public void testProvider() {
        final NoSqlAppender appender = NoSqlAppender.createAppender("myName01", null, null, null, provider);

        assertNotNull("The appender should not be null.", appender);
        assertEquals("The toString value is not correct.",
                "myName01{ manager=noSqlManager{ description=myName01, bufferSize=0, provider=" + provider + " } }",
                appender.toString());

        appender.stop();
    }
