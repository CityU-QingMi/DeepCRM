    @Test
    public void testNanoTimeIsNotSerialized1() throws Exception {
        final LogEvent event1 = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()) //
                .setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world!")) //
                .setThreadName("this must be initialized or the test fails") //
                .setNanoTime(12345678L) //
                .build();
        final LogEvent copy = new Log4jLogEvent.Builder(event1).build();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(event1);

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        
        final LogEvent actual = (LogEvent) ois.readObject();
        assertNotEquals("Different event: nanoTime", copy, actual);
        assertNotEquals("Different nanoTime", copy.getNanoTime(), actual.getNanoTime());
        assertEquals("deserialized nanoTime is zero", 0, actual.getNanoTime());
    }
