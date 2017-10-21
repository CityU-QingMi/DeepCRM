    public void testRegister() {
        LocalizedTextProvider localizedTextProvider = container.getInstance(LocalizedTextProvider.class);

        assertEquals("The form has already been processed or no token was supplied, please try again.", localizedTextProvider.findDefaultText("struts.messages.invalid.token", Locale.getDefault()));
        
        loadConfigurationProviders(new StubConfigurationProvider() {
            @Override
            public void register(ContainerBuilder builder, LocatableProperties props) throws ConfigurationException {
                props.setProperty(StrutsConstants.STRUTS_CUSTOM_I18N_RESOURCES, "testmessages,testmessages2");
                props.setProperty(StrutsConstants.STRUTS_LOCALE, "US");
            }
        });

        localizedTextProvider = container.getInstance(LocalizedTextProvider.class);

        assertEquals("Replaced message for token tag", localizedTextProvider.findDefaultText("struts.messages.invalid.token", Locale.getDefault()));
    }
