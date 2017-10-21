    public void testExcludesMethod() throws Exception {
        interceptor.setExcludeMethods("execute");

        ValidationInterceptor validationInterceptor = create();
        validationInterceptor.setExcludeMethods("execute");
        interceptor.setExcludeMethods("execute");
        validationInterceptor.intercept(invocation);
        
        assertEquals(result, interceptor.intercept(invocation));
    }
