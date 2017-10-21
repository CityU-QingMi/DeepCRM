    public void testModelDrivenActionSuccess() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("bean-validation", "modelDrivenAction", null, null);
        ModelDrivenAction action = (ModelDrivenAction) baseActionProxy.getAction();
        action.getModel().setName("name");
        action.getModel().setEmail("jogep@apache.org");
        action.getModel().getAddress().setStreet("street");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(0, fieldErrors.size());
    }
