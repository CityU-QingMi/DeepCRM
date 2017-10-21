    public void testAbstract() {
        try {
            actionProxyFactory.createActionProxy("/abstract", "test", null, null);
            fail();
        } catch (Exception e) {
            // this is what we expected
        }

        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("/nonAbstract", "test", null, null);
            assertTrue(proxy.getActionName().equals("test"));
            assertTrue(proxy.getConfig().getClassName().equals(SimpleAction.class.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
