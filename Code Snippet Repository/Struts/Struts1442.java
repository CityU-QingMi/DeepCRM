    public void testShouldAutowireAction() throws Exception {
        StaticWebApplicationContext context = new StaticWebApplicationContext();
        context.getBeanFactory().registerSingleton("bean", new TestBean());
        TestBean bean = (TestBean) context.getBean("bean");

        loadSpringApplicationContextIntoApplication(context);

        SimpleAction action = new SimpleAction();
        ActionInvocation invocation = new TestActionInvocation(action);

        ActionAutowiringInterceptor interceptor = new ActionAutowiringInterceptor();
        interceptor.setApplicationContext(context);
        interceptor.init();

        interceptor.intercept(invocation);

        assertEquals(bean, action.getBean());
    }
