    public void testIncludesAndExcludesMethodWithExcludeWildcard() throws Exception {
        interceptor.setExcludeMethods("*");
        interceptor.setIncludeMethods("execute");
        
        ValidationInterceptor validationInterceptor = create();
        validationInterceptor.setExcludeMethods("*");
        validationInterceptor.setIncludeMethods("execute");
        validationInterceptor.intercept(invocation);
        
        assertEquals(result, interceptor.intercept(invocation));
    }
