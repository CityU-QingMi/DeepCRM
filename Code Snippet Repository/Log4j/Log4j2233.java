    @Test
    public void testSerialization() throws Exception {
        final SerializedLayout layout = SerializedLayout.createLayout();
        final Throwable throwable = new LoggingException("Test");
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()) //
                .setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world!")) //
                .setThrown(throwable) //
                .build();
        final byte[] result = layout.toByteArray(event);
        assertNotNull(result);
        final FileOutputStream fos = new FileOutputStream(DAT_PATH);
        fos.write(layout.getHeader());
        fos.write(result);
        fos.close();
    }
