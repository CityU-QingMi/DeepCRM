    public void testPrepareThrowException() throws Exception {
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy mockActionProxy = new MockActionProxy();
        mockActionProxy.setMethod("submit");
        mai.setProxy(mockActionProxy);
        mai.setAction(mock.proxy());

        IllegalAccessException illegalAccessException = new IllegalAccessException();
        mock.expectAndThrow("prepareSubmit", illegalAccessException);
        mock.matchAndThrow("prepare", new RuntimeException());

        try {
            interceptor.intercept(mai);
            fail("Should not have reached this point.");
        } catch (Throwable t) {
            assertSame(illegalAccessException, t);
        }
    }
