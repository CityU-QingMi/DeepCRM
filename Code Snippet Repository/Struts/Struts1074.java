    public void testInterceptorParamInehritanceOverride() {
        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("/foo/bar", "TestInterceptorParamInehritanceOverride", null, null);
            assertEquals(1, proxy.getConfig().getInterceptors().size());

            MockInterceptor testInterceptor = (MockInterceptor) proxy.getConfig().getInterceptors().get(0).getInterceptor();
            assertEquals("foo123", testInterceptor.getExpectedFoo());
            proxy.execute();
            assertTrue(testInterceptor.isExecuted());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
