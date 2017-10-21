    @Test
    public void testNonNullValue() throws Exception {
        node.getAttributes().put("name", "foo");
        // @formatter:off
        final ValidatingPluginWithTypedBuilder validatingPlugin = (ValidatingPluginWithTypedBuilder) 
                new PluginBuilder(plugin).
                withConfiguration(new NullConfiguration()).
                withConfigurationNode(node).build();
        // @formatter:on
        assertNotNull(validatingPlugin);
        assertEquals("foo", validatingPlugin.getName());
    }
