    public void testFailsOnMethodThatThrowsException() {
        SimpleAction action = new SimpleAction();
        OgnlValueStack stack = createValueStack();
        stack.push(action);

        action.setThrowException(true);
        try {
            stack.findValue("exceptionMethod12()", true);
            fail("Failed to throw exception on EL method exception");
        } catch (Exception ex) {
            //ok
        }
    }
