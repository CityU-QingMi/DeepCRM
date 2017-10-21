    public void testWildcardNamespace() {
        RuntimeConfiguration configuration = configurationManager.getConfiguration().getRuntimeConfiguration();

        ActionConfig config = configuration.getActionConfig("/animals/dog", "commandTest");

        assertNotNull(config);
        assertTrue("Wrong class name, "+config.getClassName(),
                "com.opensymphony.xwork2.SimpleAction".equals(config.getClassName()));

        Map<String, String> p = config.getParams();
        assertTrue("Wrong parameter, "+p.get("0"), "/animals/dog".equals(p.get("0")));
        assertTrue("Wrong parameter, "+p.get("1"), "dog".equals(p.get("1")));
    }
