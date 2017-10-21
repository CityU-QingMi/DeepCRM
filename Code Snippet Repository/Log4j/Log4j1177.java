    private void appendSuppressedCount(final StringBuilder sb, final String prefix, final int count,
                                       final TextRenderer textRenderer, final String suffix) {
        textRenderer.render(prefix, sb, "Prefix");
        if (count == 1) {
            textRenderer.render("\t... ", sb, "Suppressed");
        } else {
            textRenderer.render("\t... suppressed ", sb, "Suppressed");
            textRenderer.render(Integer.toString(count), sb, "Suppressed");
            textRenderer.render(" lines", sb, "Suppressed");
        }
        renderSuffix(suffix, sb, textRenderer);
        textRenderer.render(EOL_STR, sb, "Text");
    }
