    @SuppressWarnings("")
    @Before
    public void setUp() throws Exception {
        final PluginManager manager = new PluginManager("Test");
        manager.collectPlugins();
        plugin = (PluginType<HostAndPort>) manager.getPluginType("HostAndPort");
        assertNotNull("Rebuild this module to ensure annotation processing has been done.", plugin);
        node = new Node(null, "HostAndPort", plugin);
        node.getAttributes().put("host", "localhost");
    }
