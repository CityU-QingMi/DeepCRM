    @Test
    public void testNestedPlugin() throws Exception {
        final Plugin p = FakePlugin.Nested.class.getAnnotation(Plugin.class);
        final PluginEntry nested = pluginCache.getCategory(p.category()).get(p.name().toLowerCase());
        assertNotNull(nested);
        assertEquals(p.name().toLowerCase(), nested.getKey());
        assertEquals(FakePlugin.Nested.class.getName(), nested.getClassName());
        assertEquals(p.name(), nested.getName());
        assertEquals(Plugin.EMPTY, p.elementType());
        assertEquals(p.printObject(), nested.isPrintable());
        assertEquals(p.deferChildren(), nested.isDefer());
    }
