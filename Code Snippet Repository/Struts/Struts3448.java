    public void testServiceAware() throws Exception {
        ServletContext servletContext = EasyMock.createStrictMock(ServletContext.class);
        BundleContext bundleContext = EasyMock.createStrictMock(BundleContext.class);
        ActionInvocation actionInvocation = EasyMock.createStrictMock(ActionInvocation.class);
        SomeAction someAction = new SomeAction();

        //service refs
        ServiceReference objectRef = EasyMock.createNiceMock(ServiceReference.class);
        Object someObject = new Object();

        EasyMock.expect(servletContext.getAttribute(OsgiHost.OSGI_BUNDLE_CONTEXT)).andReturn(bundleContext);
        EasyMock.expect(actionInvocation.getAction()).andReturn(someAction);
        EasyMock.expect(actionInvocation.invoke()).andReturn("");
        EasyMock.expect(bundleContext.getAllServiceReferences(Object.class.getName(), null)).andReturn(new ServiceReference[] {objectRef});
        EasyMock.expect(bundleContext.getService(objectRef)).andReturn(someObject);

        EasyMock.replay(bundleContext);
        EasyMock.replay(servletContext);
        EasyMock.replay(actionInvocation);

        OsgiInterceptor osgiInterceptor = new OsgiInterceptor();
        osgiInterceptor.setServletContext(servletContext);
        osgiInterceptor.intercept(actionInvocation);

        List<Object> objects = someAction.getServices();
        assertNotNull(objects);
        assertSame(someObject, objects.get(0));
    }
