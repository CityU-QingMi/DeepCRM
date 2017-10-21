    private void formatThrowableProxy(final StringBuilder sb, final String prefix, final String causeLabel,
                                      final ThrowableProxy throwableProxy, final List<String> ignorePackages,
                                      final TextRenderer textRenderer, final String suffix) {
        if (throwableProxy == null) {
            return;
        }
        textRenderer.render(prefix, sb, "Prefix");
        textRenderer.render(causeLabel, sb, "CauseLabel");
        throwableProxy.renderOn(sb, textRenderer);
        renderSuffix(suffix, sb, textRenderer);
        textRenderer.render(EOL_STR, sb, "Text");
        this.formatElements(sb, prefix, throwableProxy.commonElementCount,
            throwableProxy.getStackTrace(), throwableProxy.extendedStackTrace, ignorePackages, textRenderer, suffix);
        this.formatSuppressed(sb, prefix + TAB, throwableProxy.suppressedProxies, ignorePackages, textRenderer, suffix);
        this.formatCause(sb, prefix, throwableProxy.causeProxy, ignorePackages, textRenderer, suffix);
    }
