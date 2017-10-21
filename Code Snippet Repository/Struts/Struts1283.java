    public void testPrepareCalledFalse() throws Exception {
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy mockActionProxy = new MockActionProxy();
        mockActionProxy.setMethod("execute");
        mai.setProxy(mockActionProxy);
        mai.setAction(mock.proxy());

        interceptor.setAlwaysInvokePrepare("false");
        interceptor.intercept(mai);
    }
