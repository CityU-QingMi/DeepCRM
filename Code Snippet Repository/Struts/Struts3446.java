    public void testBundleContextAware() throws Exception {
        ServletContext servletContext = EasyMock.createStrictMock(ServletContext.class);
        BundleContext bundleContext = EasyMock.createStrictMock(BundleContext.class);
        ActionInvocation actionInvocation = EasyMock.createStrictMock(ActionInvocation.class);
        BundleContextAware bundleContextAware = EasyMock.createStrictMock(BundleContextAware.class);

        EasyMock.expect(servletContext.getAttribute(OsgiHost.OSGI_BUNDLE_CONTEXT)).andReturn(bundleContext);
        EasyMock.expect(actionInvocation.getAction()).andReturn(bundleContextAware);
        bundleContextAware.setBundleContext(bundleContext);
        EasyMock.expect(actionInvocation.invoke()).andReturn("");

        EasyMock.replay(bundleContextAware);
        EasyMock.replay(servletContext);
        EasyMock.replay(actionInvocation);

        OsgiInterceptor osgiInterceptor = new OsgiInterceptor();
        osgiInterceptor.setServletContext(servletContext);
        osgiInterceptor.intercept(actionInvocation);

        EasyMock.verify(bundleContextAware);
    }
