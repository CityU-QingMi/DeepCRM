    public void testChangeResultWhenNotifyAboutValidationErrors() throws Exception {
        // given
        ValidationInterceptor validationInterceptor = create();

        // when
        validationInterceptor.intercept(invocation);

        // then
        assertEquals(actionResult, interceptor.intercept(invocation));
    }
