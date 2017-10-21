    public void testSMDAliasedMethodCall2() throws Exception {
        // request
        setRequestContent("smd-15.txt");
        this.request.addHeader("Content-Type", "application/json-rpc");

        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setEnableSMD(true);
        SMDActionTest2 action = new SMDActionTest2();

        this.invocation.setAction(action);

        interceptor.intercept(this.invocation);
        // method was aliased, but was invoked with the aliased name
        // so method must be invoked
        assertFalse(this.invocation.isInvoked());
        assertTrue(action.isDoSomethingInvoked());
    }
