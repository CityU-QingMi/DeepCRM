    public void testWarnAboutInvalidProperties() {
        OgnlValueStack stack = createValueStack();
        MyAction action = new MyAction();
        action.setName("Don");
        stack.push(action);

        // how to test the warning was logged?
        assertEquals("Don", stack.findValue("name", String.class));
        assertEquals(null, stack.findValue("address", String.class));
        // should log warning
        assertEquals(null, stack.findValue("address.invalidProperty", String.class));

        // if country is null, OGNL throws an exception
/**/
/**/
        // should log warning
        assertEquals(null, stack.findValue("address.country.id", String.class));
        assertEquals(null, stack.findValue("address.country.name", String.class));
    }
