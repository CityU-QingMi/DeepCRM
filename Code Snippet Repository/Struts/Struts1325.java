    public void testSetBigIndexedValue() {
        ValueStack stack = ActionContext.getContext().getValueStack();
        Map stackContext = stack.getContext();
        stackContext.put(ReflectionContextState.CREATE_NULL_OBJECTS, Boolean.FALSE);
        stackContext.put(ReflectionContextState.DENY_METHOD_EXECUTION, Boolean.TRUE);
        stackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

        User user = new User();
        stack.push(user);

        // indexed string w/ existing array
        user.setList(new ArrayList());

        String[] foo = new String[]{"asdf"};
        ((OgnlValueStack)stack).setDevMode("true");
        try {
            stack.setValue("list.1114778947765", foo);
            fail("non-valid expression: list.1114778947765"); 
        }
        catch(RuntimeException ex) {
            ; // it's oke
        }
        
        try {
            stack.setValue("1114778947765", foo);
            fail("non-valid expression: 1114778947765"); 
        }
        catch(RuntimeException ex) {
            ;
        }
        
        try {
            stack.setValue("1234", foo);
            fail("non-valid expression: 1114778947765"); 
        }
        catch(RuntimeException ex) {
            ;
        }
        
        ((OgnlValueStack)stack).setDevMode("false");
        stack.setValue("list.1114778947765", foo);
        stack.setValue("1114778947765", foo);
        stack.setValue("1234", foo);
    }
