    @Test
    public void testNanoTimeIsNotSerialized2() throws Exception {
        final LogEvent event1 = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()) //
                .setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world!")) //
                .setThreadId(1) // this must be initialized or the test fails
                .setThreadName("this must be initialized or the test fails") //
                .setThreadPriority(2) // this must be initialized or the test fails
                .setNanoTime(0) //
                .build();
        final LogEvent event2 = new Log4jLogEvent.Builder(event1).build();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(event1);

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        
        final LogEvent actual = (LogEvent) ois.readObject();
        assertEquals("both zero nanoTime", event2, actual);
    }
