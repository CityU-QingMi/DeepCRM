    public void testIncludesAndExcludesMethodWithIncludeWildcard() throws Exception {
        interceptor.setExcludeMethods("input,validate");
        interceptor.setIncludeMethods("*");
        
        ValidationInterceptor validationInterceptor = create();
        validationInterceptor.setExcludeMethods("input,validate");
        validationInterceptor.setIncludeMethods("*");
        validationInterceptor.intercept(invocation);
        
        assertEquals(result, interceptor.intercept(invocation));
    }
