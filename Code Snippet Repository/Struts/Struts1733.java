    public void testRegister_NoLocale() {

        ContainerBuilder builder = new ContainerBuilder();
        builder.constant("foo", "bar");

        PropertiesConfigurationProvider prov = new PropertiesConfigurationProvider();
        prov.register(builder, new LocatableProperties());

        Container container = builder.create(true);

        String localeStr = container.getInstance(String.class, StrutsConstants.STRUTS_LOCALE);

        assertNull(localeStr);
    }
