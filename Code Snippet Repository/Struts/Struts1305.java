    public void testNotChangeResultWhenNotifyAboutValidationError() throws Exception {
        // given
        actionResult = Action.INPUT;
        ValidationInterceptor validationInterceptor = create();

        // when
        validationInterceptor.intercept(invocation);

        // then
        Assert.assertEquals(Action.INPUT, interceptor.intercept(invocation));
    }
