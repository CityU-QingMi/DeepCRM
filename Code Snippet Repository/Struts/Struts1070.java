    public void testMultipleInheritance() {
        try {
            ActionProxy proxy;
            proxy = actionProxyFactory.createActionProxy("multipleInheritance", "test", null, null);
            assertNotNull(proxy);
            proxy = actionProxyFactory.createActionProxy("multipleInheritance", "Foo", null, null);
            assertNotNull(proxy);
            proxy = actionProxyFactory.createActionProxy("multipleInheritance", "testMultipleInheritance", null, null);
            assertNotNull(proxy);
            assertEquals(5, proxy.getConfig().getInterceptors().size());
            assertEquals(2, proxy.getConfig().getResults().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
