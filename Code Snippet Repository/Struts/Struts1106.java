    public void testGlobalResultInheritenceTest() throws Exception {
        ConfigurationProvider provider = buildConfigurationProvider("com/opensymphony/xwork2/config/providers/xwork-test-global-result-inheritence.xml");

        ConfigurationManager configurationManager = new ConfigurationManager(Container.DEFAULT_NAME);
        configurationManager.addContainerProvider(new XWorkConfigurationProvider());
        configurationManager.addContainerProvider(provider);
        Configuration configuration = configurationManager.getConfiguration();

        ActionConfig parentActionConfig = configuration.getRuntimeConfiguration().getActionConfig("/base", "parentAction");
        ActionConfig anotherActionConfig = configuration.getRuntimeConfiguration().getActionConfig("/base", "anotherAction");
        ActionConfig childActionConfig = configuration.getRuntimeConfiguration().getActionConfig("/base", "childAction");

        ResultConfig parentResultConfig1 = parentActionConfig.getResults().get("mockResult1");
        ResultConfig parentResultConfig2 = parentActionConfig.getResults().get("mockResult2");
        ResultConfig anotherResultConfig1 = anotherActionConfig.getResults().get("mockResult1");
        ResultConfig anotherResultConfig2 = anotherActionConfig.getResults().get("mockResult2");
        ResultConfig childResultConfig1 = childActionConfig.getResults().get("mockResult1");
        ResultConfig childResultConfig2 = childActionConfig.getResults().get("mockResult2");

        System.out.println(parentResultConfig1.getParams().get("identity"));
        System.out.println(parentResultConfig2.getParams().get("identity"));
        System.out.println(anotherResultConfig1.getParams().get("identity"));
        System.out.println(anotherResultConfig2.getParams().get("identity"));
        System.out.println(childResultConfig1.getParams().get("identity"));
        System.out.println(childResultConfig2.getParams().get("identity"));

        assertFalse(parentResultConfig1 == anotherResultConfig1);
        assertFalse(parentResultConfig2 == anotherResultConfig2);

        assertFalse(parentResultConfig1 == childResultConfig1);
        assertTrue(parentResultConfig2 == childResultConfig2);
    }
