    private void testFullAnsiEmptyConfig(final ThrowableFormatOptions tfo) {
        final TextRenderer textRenderer = tfo.getTextRenderer();
        Assert.assertNotNull(textRenderer);
        Assert.assertTrue(textRenderer instanceof JAnsiTextRenderer);
        final JAnsiTextRenderer jansiRenderer = (JAnsiTextRenderer) textRenderer;
        final Map<String, Code[]> styleMap = jansiRenderer.getStyleMap();
        // We have defaults
        Assert.assertFalse(styleMap.isEmpty());
        Assert.assertNotNull(styleMap.get("Name"));
    }
