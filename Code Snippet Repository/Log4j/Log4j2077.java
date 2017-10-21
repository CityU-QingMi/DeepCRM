    @Test
    public void testNullDefaultValue() throws Exception {
        // @formatter:off
        final ValidatingPluginWithTypedBuilder validatingPlugin = (ValidatingPluginWithTypedBuilder) 
                new PluginBuilder(plugin).
                withConfiguration(new NullConfiguration()).
                withConfigurationNode(node).build();
        // @formatter:on
        assertNull(validatingPlugin);
    }
