     public void testBundleContextAwareNegative() throws Exception {
        ServletContext servletContext = EasyMock.createStrictMock(ServletContext.class);
        ActionInvocation actionInvocation = EasyMock.createStrictMock(ActionInvocation.class);
        BundleContextAware bundleContextAware = EasyMock.createStrictMock(BundleContextAware.class);

        EasyMock.expect(servletContext.getAttribute(OsgiHost.OSGI_BUNDLE_CONTEXT)).andReturn(null);
        EasyMock.expect(actionInvocation.invoke()).andReturn("");

        EasyMock.replay(bundleContextAware);
        EasyMock.replay(servletContext);
        EasyMock.replay(actionInvocation);

        OsgiInterceptor osgiInterceptor = new OsgiInterceptor();
        osgiInterceptor.setServletContext(servletContext);
        osgiInterceptor.intercept(actionInvocation);

        EasyMock.verify(bundleContextAware);
    }
