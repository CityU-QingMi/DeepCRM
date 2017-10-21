    public String getExtendedStackTraceAsString(final List<String> ignorePackages, final TextRenderer textRenderer, final String suffix) {
        final StringBuilder sb = new StringBuilder(1024);
        textRenderer.render(name, sb, "Name");
        textRenderer.render(": ", sb, "NameMessageSeparator");
        textRenderer.render(this.message, sb, "Message");
        renderSuffix(suffix, sb, textRenderer);
        textRenderer.render(EOL_STR, sb, "Text");
        final StackTraceElement[] causedTrace = this.throwable != null ? this.throwable.getStackTrace() : null;
        this.formatElements(sb, Strings.EMPTY, 0, causedTrace, this.extendedStackTrace, ignorePackages, textRenderer, suffix);
        this.formatSuppressed(sb, TAB, this.suppressedProxies, ignorePackages, textRenderer, suffix);
        this.formatCause(sb, Strings.EMPTY, this.causeProxy, ignorePackages, textRenderer, suffix);
        return sb.toString();
    }
