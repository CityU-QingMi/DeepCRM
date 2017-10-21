    public void testRegister_DifferentLocale() {

        ContainerBuilder builder = new ContainerBuilder();
        builder.constant("foo", "bar");
        builder.constant("struts.locale", "de_DE");

        PropertiesConfigurationProvider prov = new PropertiesConfigurationProvider();
        prov.register(builder, new LocatableProperties());

        Container container = builder.create(true);

        String localeStr = container.getInstance(String.class, StrutsConstants.STRUTS_LOCALE);
        Locale locale = LocaleUtils.toLocale(localeStr);

        assertNotNull(locale);
        assertEquals("DE", locale.getCountry());
        assertEquals("de", locale.getLanguage());

    }
