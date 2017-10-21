	@Test
    public void getActionProxy() throws Exception {
        //set parameters before calling getActionProxy
        request.setParameter("name", "FD");
        
        ActionProxy proxy = getActionProxy("/test/testAction.action");
        Assert.assertNotNull(proxy);

        JUnitTestAction action = (JUnitTestAction) proxy.getAction();
        Assert.assertNotNull(action);

        String result = proxy.execute();
        Assert.assertEquals(Action.SUCCESS, result);
        Assert.assertEquals("FD", action.getName());
    }
