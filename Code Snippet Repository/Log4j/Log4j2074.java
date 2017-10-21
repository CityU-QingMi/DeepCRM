    @Test
    public void testNonNullValue() throws Exception {
        node.getAttributes().put("name", "foo");
        final ValidatingPluginWithGenericBuilder validatingPlugin = (ValidatingPluginWithGenericBuilder) new PluginBuilder(plugin)
            .withConfiguration(new NullConfiguration())
            .withConfigurationNode(node)
            .build();
        assertNotNull(validatingPlugin);
        assertEquals("foo", validatingPlugin.getName());
    }
