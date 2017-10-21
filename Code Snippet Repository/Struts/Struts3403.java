    public void testGetActionProxy() throws Exception {
        //set parameters before calling getActionProxy
        request.setParameter("name", "FD");
        
        ActionProxy proxy = getActionProxy("/test/testAction.action");
        assertNotNull(proxy);

        JUnitTestAction action = (JUnitTestAction) proxy.getAction();
        assertNotNull(action);

        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        assertEquals("FD", action.getName());
    }
