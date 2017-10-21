    public void testModelDrivenActionSkipValidationByInterface() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("bean-validation", "modelDrivenActionSkipValidationByInterface", null, null);
        ModelDrivenAction action = (ModelDrivenAction) baseActionProxy.getAction();
        action.getModel().setName(null);
        action.getModel().setEmail(null);
        action.getModel().getAddress().setStreet(null);
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(0, fieldErrors.size());
    }
