    @Test
    public void testAccessToEchoerMBean() throws Exception
    {
        ObjectName echoerName = new ObjectName("org.eclipse.jetty.test.jmx:type=echoer,context=jmx-webapp,id=0");
        // Get initial count
        int count = getIntegerAttribute(echoerName, "count");
        // Operations
        Object val = _mbsc.invoke(echoerName, "echo", new Object[]{"Its Me"}, new String[]{String.class.getName()});
        assertThat("echo() return", val.toString(), is("Its Me"));
        // Attributes
        assertThat("count", getIntegerAttribute(echoerName, "count"), is(count + 1));
        assertThat("foo", getStringAttribute(echoerName, "foo"), is("foo-ish"));
    }
