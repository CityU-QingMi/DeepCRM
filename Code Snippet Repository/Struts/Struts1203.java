    public void testActionErrorsNotCopiedAfterChain() throws Exception {
        SimpleAction action1 = new SimpleAction();
        SimpleAction action2 = new SimpleAction();
        action1.addActionError("foo");
        mockInvocation.matchAndReturn("getAction", action2);
        stack.push(action1);
        stack.push(action2);

        interceptor.intercept(invocation);

        assertEquals(Collections.EMPTY_LIST, action2.getActionErrors());
        action2.addActionError("bar");
        assertEquals(1, action1.getActionErrors().size());
        assertEquals(1, action2.getActionErrors().size());
        assertTrue(action2.getActionErrors().contains("bar"));
        assertFalse(action2.getActionErrors().contains("foo"));
    }
