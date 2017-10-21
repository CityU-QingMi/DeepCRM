    public void testPreResultListenerAddition() throws Exception {
        // given
        ActionInvocation invocation = EasyMock.createNiceMock(ActionInvocation.class);
        CookieProviderInterceptor interceptor = new CookieProviderInterceptor();

        invocation.addPreResultListener(interceptor);

        EasyMock.replay(invocation);

        // when
        interceptor.intercept(invocation);

        // then
        EasyMock.verify(invocation);
    }
