    public void testCallMethodThatThrowsExceptionTwice() {
        SimpleAction action = new SimpleAction();
        OgnlValueStack stack = createValueStack();
        stack.push(action);

        action.setThrowException(true);
        assertNull(stack.findValue("exceptionMethod1()"));
        action.setThrowException(false);
        assertEquals("OK", stack.findValue("exceptionMethod()"));
    }
