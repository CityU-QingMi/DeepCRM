    public void testBeanListSingleValue() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("beanList.name", new String[] { "Superman" });

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("",
                MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        proxy.execute();
        SimpleAction action = (SimpleAction) proxy.getAction();
        assertNotNull(action);
        assertNotNull(action.getBeanList());
        assertFalse(action.getBeanList().isEmpty());
    }
