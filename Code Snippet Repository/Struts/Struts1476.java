    @Override
    protected void setUp() throws Exception {
        super.setUp();
        XmlConfigurationProvider provider = new XmlConfigurationProvider("xwork-sample.xml");
        container.inject(provider);
        loadConfigurationProviders(provider);

        localizedTextProvider = container.getInstance(LocalizedTextProvider.class);
        
        ActionContext.getContext().setLocale(Locale.US);
    }
