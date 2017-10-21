    public void testIncludesAndExcludesMethod() throws Exception {
        interceptor.setExcludeMethods("execute,input,validate");
        interceptor.setIncludeMethods("execute");
        
        ValidationInterceptor validationInterceptor = create();
        validationInterceptor.setExcludeMethods("execute,input,validate");
        validationInterceptor.setIncludeMethods("execute");
        validationInterceptor.intercept(invocation);
        
        assertEquals(result, interceptor.intercept(invocation));
    }
