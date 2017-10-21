    public void selfRegister() {
        //this cannot be done in the constructor, as it causes an infinite loop
        builder.factory(Configuration.class, MockConfiguration.class, Scope.SINGLETON);
        LocatableProperties props = new LocatableProperties();
        new XWorkConfigurationProvider().register(builder, props);
        builder.constant(XWorkConstants.DEV_MODE, "false");
        builder.constant(XWorkConstants.RELOAD_XML_CONFIGURATION, "true");
        builder.constant(XWorkConstants.ENABLE_OGNL_EXPRESSION_CACHE, "true");
        builder.constant(StrutsConstants.STRUTS_ENABLE_DYNAMIC_METHOD_INVOCATION, "false");
        container = builder.create(true);
    }
