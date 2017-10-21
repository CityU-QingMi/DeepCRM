    public void testModelDrivenAction() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("bean-validation", "modelDrivenAction", null, null);
        ModelDrivenAction action = (ModelDrivenAction) baseActionProxy.getAction();
        action.getModel().setName(null);
        action.getModel().setEmail(null);
        action.getModel().getAddress().setStreet(null);
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(3, fieldErrors.size());
        assertTrue(fieldErrors.get("name").size() > 0);
        assertEquals(fieldErrors.get("name").get(0), "nameNotNull");
        assertTrue(fieldErrors.get("email").size() > 0);
        assertEquals(fieldErrors.get("email").get(0), "emailNotNull");
        assertTrue(fieldErrors.get("address.street").size() > 0);
        assertEquals(fieldErrors.get("address.street").get(0), "streetNotNull");
    }
