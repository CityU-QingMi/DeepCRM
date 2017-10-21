    @Test
    public void testAccessToPingerMBean() throws Exception
    {
        ObjectName pingerName = new ObjectName("org.eclipse.jetty.test.jmx:type=pinger,context=jmx-webapp,id=0");
        // Get initial count
        int count = getIntegerAttribute(pingerName, "count");
        // Operations
        Object val = _mbsc.invoke(pingerName, "ping", null, null);
        assertThat("ping() return", val.toString(), startsWith("Pong"));
        // Attributes
        assertThat("count", getIntegerAttribute(pingerName, "count"), is(count + 1));
    }
