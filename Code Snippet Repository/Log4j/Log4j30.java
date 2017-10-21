    @BeforeClass
    public static void setUpClass() {
        rbUS = ResourceBundle.getBundle("L7D", new Locale("en", "US"));
        assertNotNull(rbUS);

        rbFR = ResourceBundle.getBundle("L7D", new Locale("fr", "FR"));
        assertNotNull("Got a null resource bundle.", rbFR);

        rbCH = ResourceBundle.getBundle("L7D", new Locale("fr", "CH"));
        assertNotNull("Got a null resource bundle.", rbCH);

        ConfigurationFactory.setConfigurationFactory(configurationFactory);
    }
