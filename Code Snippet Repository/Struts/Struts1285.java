    public void testFirstCallPrepareDoIsTrue() throws Exception {
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy mockActionProxy = new MockActionProxy();
        mockActionProxy.setMethod("submit");
        mai.setProxy(mockActionProxy);
        mai.setAction(mock.proxy());
        mock.expect("prepareSubmit");
        mock.expect("prepare");

        interceptor.setFirstCallPrepareDo("true");
        interceptor.intercept(mai);
    }
