    @Override
    protected void setUp() throws Exception {
        super.setUp();
        XmlConfigurationProvider configurationProvider = new XmlConfigurationProvider("xwork-test-beans.xml");
        container.inject(configurationProvider);
        loadConfigurationProviders(configurationProvider, new MockConfigurationProvider());

        ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();
        stack.getContext().put(ActionContext.CONTAINER, container);
        ActionContext.setContext(new ActionContext(stack.getContext()));
    }
