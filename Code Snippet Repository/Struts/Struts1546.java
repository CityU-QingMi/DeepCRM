    @Override
    protected void setUp() throws Exception {
        super.setUp();
        XmlConfigurationProvider provider = new XmlConfigurationProvider("xwork-default.xml");
        container.inject(provider);
        loadConfigurationProviders(provider, new MockConfigurationProvider());
        val = new DoubleRangeFieldValidator();
        val.setValueStack(ActionContext.getContext().getValueStack());
        ActionContext.getContext().setParameters(HttpParameters.create().build());
        tpf = container.getInstance(TextProviderFactory.class);
    }
