    @SuppressWarnings("")
    @Before
    public void setUp() throws Exception {
        final PluginManager manager = new PluginManager("Test");
        manager.collectPlugins();
        plugin = (PluginType<ValidatingPluginWithTypedBuilder>) manager
                .getPluginType("ValidatingPluginWithTypedBuilder");
        assertNotNull("Rebuild this module to make sure annotaion processing kicks in.", plugin);
        node = new Node(null, "Validator", plugin);
    }
