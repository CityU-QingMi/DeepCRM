    public void testUsingDefaultInterceptorThatAliasPropertiesAreCopied() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("aliasSource", "source here");

        XmlConfigurationProvider provider = new XmlConfigurationProvider("xwork-sample.xml");
        container.inject(provider);
        loadConfigurationProviders(provider);
        ActionProxy proxy = actionProxyFactory.createActionProxy("", "aliasTest", null, params);
        SimpleAction actionOne = (SimpleAction) proxy.getAction();
        actionOne.setAliasSource("name to be copied");
        actionOne.setFoo(17);
        actionOne.setBar(23);
        proxy.execute();
        assertEquals(actionOne.getAliasSource(), actionOne.getAliasDest());
    }
