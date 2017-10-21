    private void formatElements(final StringBuilder sb, final String prefix, final int commonCount,
                                final StackTraceElement[] causedTrace, final ExtendedStackTraceElement[] extStackTrace,
                                final List<String> ignorePackages, final TextRenderer textRenderer, final String suffix) {
        if (ignorePackages == null || ignorePackages.isEmpty()) {
            for (final ExtendedStackTraceElement element : extStackTrace) {
                this.formatEntry(element, sb, prefix, textRenderer, suffix);
            }
        } else {
            int count = 0;
            for (int i = 0; i < extStackTrace.length; ++i) {
                if (!this.ignoreElement(causedTrace[i], ignorePackages)) {
                    if (count > 0) {
                        appendSuppressedCount(sb, prefix, count, textRenderer, suffix);
                        count = 0;
                    }
                    this.formatEntry(extStackTrace[i], sb, prefix, textRenderer, suffix);
                } else {
                    ++count;
                }
            }
            if (count > 0) {
                appendSuppressedCount(sb, prefix, count, textRenderer, suffix);
            }
        }
        if (commonCount != 0) {
            textRenderer.render(prefix, sb, "Prefix");
            textRenderer.render("\t... ", sb, "More");
            textRenderer.render(Integer.toString(commonCount), sb, "More");
            textRenderer.render(" more", sb, "More");
            renderSuffix(suffix, sb, textRenderer);
            textRenderer.render(EOL_STR, sb, "Text");
        }
    }
