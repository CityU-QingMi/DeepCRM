    public void testModelDrivenValidation() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("count", new String[]{"11"});

        Map<String, Object> context = new HashMap<>();
        context.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        XmlConfigurationProvider provider = new XmlConfigurationProvider("xwork-sample.xml");
        container.inject(provider);
        loadConfigurationProviders(provider);
        ActionProxy proxy = actionProxyFactory.createActionProxy(null, "TestModelDrivenValidation", null, context);
        assertEquals(Action.SUCCESS, proxy.execute());

        ModelDrivenAction action = (ModelDrivenAction) proxy.getAction();
        assertTrue(action.hasFieldErrors());
        assertTrue(action.getFieldErrors().containsKey("count"));
        assertEquals("count must be between 1 and 10, current value is 11.", ((List) action.getFieldErrors().get("count")).get(0));
    }
