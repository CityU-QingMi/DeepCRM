    public void testSetAutowireType() throws Exception {
        XmlConfigurationProvider prov = new XmlConfigurationProvider("xwork-default.xml");
        container.inject(prov);
        prov.setThrowExceptionOnDuplicateBeans(false);
        XmlConfigurationProvider c = new XmlConfigurationProvider("com/opensymphony/xwork2/spring/xwork-autowire.xml");
        container.inject(c);
        loadConfigurationProviders(c, prov);

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
