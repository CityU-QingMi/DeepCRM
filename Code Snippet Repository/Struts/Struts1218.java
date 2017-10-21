    public void testIncludesAndExcludesMethodWithIncludeWildcardAndNoMatches() throws Exception {
        interceptor.setExcludeMethods("execute,input,validate");
        interceptor.setIncludeMethods("*");
        
        ValidationInterceptor validationInterceptor = create();
        validationInterceptor.setExcludeMethods("execute,input,validate");
        validationInterceptor.setIncludeMethods("*");
        validationInterceptor.intercept(invocation);
        
        assertEquals(result, interceptor.intercept(invocation));
    }
