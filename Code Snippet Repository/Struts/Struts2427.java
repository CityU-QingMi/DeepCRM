    public void testModelDrivenActionSize() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("bean-validation", "modelDrivenAction", null, null);
        ModelDrivenAction action = (ModelDrivenAction) baseActionProxy.getAction();
        action.getModel().setName("j");
        action.getModel().setEmail("jogep@apache.org");
        action.getModel().getAddress().setStreet("st");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();
        System.out.println(fieldErrors);
        assertNotNull(fieldErrors);
        assertEquals(2, fieldErrors.size());
        assertTrue(fieldErrors.get("name").size() > 0);
        assertEquals(fieldErrors.get("name").get(0), "nameSize");
        assertTrue(fieldErrors.get("address.street").size() > 0);
        assertEquals(fieldErrors.get("address.street").get(0), "streetSize");
    }
