    @Override
    public void setUp() throws Exception {
        super.setUp();

        context = new HashMap<>();
        // Set up XWork
        XmlConfigurationProvider provider = new XmlConfigurationProvider("com/opensymphony/xwork2/spring/actionContext-xwork.xml");
        container.inject(provider);
        loadConfigurationProviders(provider);
    }
