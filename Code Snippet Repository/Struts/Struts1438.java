    public void testShouldUseApplicationContextToApplyAspectsToGeneratedBeans() throws Exception {
        sac.registerSingleton("debugInterceptor", DebugInterceptor.class, new MutablePropertyValues());

        MutablePropertyValues values = new MutablePropertyValues();
        values.addPropertyValue("beanNames", new String[]{"*Action"});
        values.addPropertyValue("interceptorNames", new String[]{"debugInterceptor"});
        sac.registerSingleton("proxyFactory", BeanNameAutoProxyCreator.class, values);

        sac.refresh();

        ActionConfig actionConfig = new ActionConfig.Builder("", "", SimpleAction.class.getName()).build();
        Action action = (Action) objectFactory.buildBean(actionConfig.getClassName(), null);

        assertNotNull("Bean should not be null", action);
        System.out.println("Action class is: " + action.getClass().getName());
        assertTrue("Action should have been advised", action instanceof Advised);
    }
