    public void testSMDAliasedMethodCall1() throws Exception {
        // request
        setRequestContent("smd-14.txt");
        this.request.addHeader("Content-Type", "application/json-rpc");

        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setEnableSMD(true);
        SMDActionTest2 action = new SMDActionTest2();

        this.invocation.setAction(action);

        interceptor.intercept(this.invocation);
        // method was aliased, but was invoked with the regular name
        // so method must not be invoked
        assertFalse(this.invocation.isInvoked());
        assertFalse(action.isDoSomethingInvoked());
    }
