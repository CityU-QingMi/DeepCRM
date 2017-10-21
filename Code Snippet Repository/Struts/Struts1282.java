    public void testPrepareCalledDefault() throws Exception {
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy mockActionProxy = new MockActionProxy();
        mockActionProxy.setMethod("execute");
        mai.setProxy(mockActionProxy);
        mai.setAction(mock.proxy());
        mock.expect("prepare");

        interceptor.intercept(mai);
    }
