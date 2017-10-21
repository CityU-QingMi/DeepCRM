    @Test
    public void testFullAnsiWithCustomStyle() {
        final ThrowableFormatOptions tfo = test(new String[] { "full", "ansi(Warning=red)" },
                Integer.MAX_VALUE, Strings.LINE_SEPARATOR, null);
        final TextRenderer textRenderer = tfo.getTextRenderer();
        Assert.assertNotNull(textRenderer);
        Assert.assertTrue(textRenderer instanceof JAnsiTextRenderer);
        final JAnsiTextRenderer jansiRenderer = (JAnsiTextRenderer) textRenderer;
        final Map<String, Code[]> styleMap = jansiRenderer.getStyleMap();
        Assert.assertArrayEquals(new Code[] { Code.RED }, styleMap.get("Warning"));
    }
