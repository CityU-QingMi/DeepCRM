    @Test
    public void testNonNullValue() throws Exception {
        node.getAttributes().put("thing", "thing1");
        node.getAttributes().put("foo1", "foo1");
        final PluginWithGenericSubclassFoo1Builder validatingPlugin = (PluginWithGenericSubclassFoo1Builder) new PluginBuilder(plugin)
            .withConfiguration(new NullConfiguration())
            .withConfigurationNode(node)
            .build();
        assertNotNull(validatingPlugin);
        assertEquals("thing1", validatingPlugin.getThing());
        assertEquals("foo1", validatingPlugin.getFoo1());
    }
