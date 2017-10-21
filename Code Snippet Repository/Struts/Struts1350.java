    public void testCallMethodWithNullArg() {
        SimpleAction action = new SimpleAction();
        OgnlValueStack stack = createValueStack();
        stack.push(action);

        stack.findValue("setName(blah)");
        assertNull(action.getName());

        action.setBlah("blah");
        stack.findValue("setName(blah)");
        assertEquals("blah", action.getName());
    }
