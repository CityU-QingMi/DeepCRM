    public void setUp() throws Exception {
        super.setUp();

        initDispatcher(new HashMap<String,String>(){{
            put("configProviders", TestConfigurationProvider.class.getName());
        }});
        createMocks();


    }
