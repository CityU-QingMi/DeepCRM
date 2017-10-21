    public void testFieldAction() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("bean-validation", "fieldAction", null, null);
        FieldAction action = (FieldAction) baseActionProxy.getAction();
        action.setTest(" ");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertTrue(fieldErrors.get("test").size() > 0);
    }
