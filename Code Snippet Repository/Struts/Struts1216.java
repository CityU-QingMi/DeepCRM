    public void testIncludesAndExcludesMethodAllWildCarded() throws Exception {
        interceptor.setExcludeMethods("*");
        interceptor.setIncludeMethods("*");
        
        ValidationInterceptor validationInterceptor = create();
        validationInterceptor.setExcludeMethods("*");
        validationInterceptor.setIncludeMethods("*");
        validationInterceptor.intercept(invocation);
        
        assertEquals(result, interceptor.intercept(invocation));
    }
