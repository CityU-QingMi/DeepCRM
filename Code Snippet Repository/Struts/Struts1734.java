    public void testDefaultSettings() throws Exception {
        // given
        PropertiesConfigurationProvider prov = new PropertiesConfigurationProvider();
        LocatableProperties props = new LocatableProperties();
        prov.register(new ContainerBuilder(), props);

        // when
        Object encoding = props.get(StrutsConstants.STRUTS_I18N_ENCODING);

        // then
        assertEquals("ISO-8859-1", encoding);
    }
