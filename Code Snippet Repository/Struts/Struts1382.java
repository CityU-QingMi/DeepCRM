    public void testDefaultType() {
        OgnlValueStack stack = createValueStack();
        stack.setDefaultType(String.class);
        stack.push("Hello World");

        assertEquals("Hello World", stack.findValue("top"));
        assertEquals(null, stack.findValue(null));

        stack.setDefaultType(Integer.class);
        stack.push(new Integer(123));
        assertEquals(new Integer(123), stack.findValue("top"));
    }
