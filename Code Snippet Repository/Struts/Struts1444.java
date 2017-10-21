    public void testLoadsApplicationContextUsingWebApplicationContextUtils() throws Exception {
        StaticWebApplicationContext appContext = new StaticWebApplicationContext();

        loadSpringApplicationContextIntoApplication(appContext);

        ActionAutowiringInterceptor interceptor = new ActionAutowiringInterceptor();
        interceptor.init();

        SimpleAction action = new SimpleAction();
        ActionInvocation invocation = new TestActionInvocation(action);

        interceptor.intercept(invocation);

        ApplicationContext loadedContext = interceptor.getApplicationContext();

        assertEquals(appContext, loadedContext);
    }
