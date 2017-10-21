    public void testIfApplicationContextIsNullThenBeanWillNotBeWiredUp() throws Exception {
        Map<String, Object> context = new HashMap<>();
        context.put(ActionContext.APPLICATION, new HashMap());
        ActionContext actionContext = new ActionContext(context);
        ActionContext.setContext(actionContext);

        ActionAutowiringInterceptor interceptor = new ActionAutowiringInterceptor();
        interceptor.init();

        SimpleAction action = new SimpleAction();
        ActionInvocation invocation = new TestActionInvocation(action);
        TestBean bean = action.getBean();

        // If an exception is thrown here, things are going to go wrong in
        // production
        interceptor.intercept(invocation);

        assertEquals(bean, action.getBean());
    }
