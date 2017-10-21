    public void testWildcardName() {
        RuntimeConfiguration configuration = configurationManager.getConfiguration().getRuntimeConfiguration();

        ActionConfig config = configuration.getActionConfig("", "WildCard/Simple/input");
        
        assertNotNull(config);
        assertTrue("Wrong class name, "+config.getClassName(), 
                "com.opensymphony.xwork2.SimpleAction".equals(config.getClassName()));
        assertTrue("Wrong method name", "input".equals(config.getMethodName()));
        
        Map<String, String> p = config.getParams();
        assertTrue("Wrong parameter, "+p.get("foo"), "Simple".equals(p.get("foo")));
        assertTrue("Wrong parameter, "+p.get("bar"), "input".equals(p.get("bar")));
    }
