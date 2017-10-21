    public void testModelDrivenAction() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "modelDrivenAction", null, null);
        ModelDrivenAction action = (ModelDrivenAction) baseActionProxy.getAction();
        action.getModel().setName(null);
        action.getModel().setEmail(null);
        action.getModel().getAddress().setStreet("short");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(5, fieldErrors.size()); // 5: as there will be field errors for 'model' and 'address' themselves
        assertValue(fieldErrors, "name", Arrays.asList("name cannot be null"));
        assertValue(fieldErrors, "email", Arrays.asList("email cannot be null"));
        assertValue(fieldErrors, "address.street", Arrays.asList("street cannot be smaller than 7 characters"));

    }
