    public void testFirstCallPrepareDoIsFalse() throws Exception {
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy mockActionProxy = new MockActionProxy();
        mockActionProxy.setMethod("submit");
        mai.setProxy(mockActionProxy);
        mai.setAction(mock.proxy());
        mock.expect("prepare");
        mock.expect("prepareSubmit");

        interceptor.setFirstCallPrepareDo("false");
        interceptor.intercept(mai);
    }
