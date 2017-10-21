    public void testModelDrivenActionEmailField() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("bean-validation", "modelDrivenAction", null, null);
        ModelDrivenAction action = (ModelDrivenAction) baseActionProxy.getAction();
        action.getModel().setName("name");
        action.getModel().setEmail("notamail");
        action.getModel().getAddress().setStreet("street");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertTrue(fieldErrors.get("email").size() > 0);
        assertEquals(fieldErrors.get("email").get(0), "emailNotValid");
    }
