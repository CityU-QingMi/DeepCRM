    @Test
    public void testFullAnsiWithCustomComplexStyles() {
        final ThrowableFormatOptions tfo = test(
                new String[] { "full", "ansi(Warning=red Key=blue,bg_red Value=cyan,bg_black,underline)" }, Integer.MAX_VALUE,
                Strings.LINE_SEPARATOR, null);
        final TextRenderer textRenderer = tfo.getTextRenderer();
        Assert.assertNotNull(textRenderer);
        Assert.assertTrue(textRenderer instanceof JAnsiTextRenderer);
        final JAnsiTextRenderer jansiRenderer = (JAnsiTextRenderer) textRenderer;
        final Map<String, Code[]> styleMap = jansiRenderer.getStyleMap();
        Assert.assertArrayEquals(new Code[] { Code.RED }, styleMap.get("Warning"));
        Assert.assertArrayEquals(new Code[] { Code.BLUE, Code.BG_RED }, styleMap.get("Key"));
        Assert.assertArrayEquals(new Code[] { Code.CYAN, Code.BG_BLACK, Code.UNDERLINE }, styleMap.get("Value"));
    }
