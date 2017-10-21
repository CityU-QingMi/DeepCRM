    public void testExpOverrides() {
        Map overrides = new HashMap();
        overrides.put("claus", "top");

        OgnlValueStack stack = createValueStack();
        stack.setExprOverrides(overrides);
        stack.push("Hello World");

        assertEquals("Hello World", stack.findValue("claus"));
        assertEquals("Hello World", stack.findString("claus"));
        assertEquals("Hello World", stack.findValue("top"));
        assertEquals("Hello World", stack.findString("top"));

        assertEquals("Hello World", stack.findValue("claus", String.class));
        assertEquals("Hello World", stack.findValue("top", String.class));

        stack.getContext().put("santa", "Hello Santa");
        assertEquals("Hello Santa", stack.findValue("santa", String.class));
        assertEquals(null, stack.findValue("unknown", String.class));
    }
