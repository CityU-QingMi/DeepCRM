    public String getCauseStackTraceAsString(final List<String> ignorePackages, final TextRenderer textRenderer, final String suffix) {
        final StringBuilder sb = new StringBuilder();
        if (this.causeProxy != null) {
            this.formatWrapper(sb, this.causeProxy, ignorePackages, textRenderer, suffix);
            sb.append(WRAPPED_BY_LABEL);
            renderSuffix(suffix, sb, textRenderer);
        }
        this.renderOn(sb, textRenderer);
        renderSuffix(suffix, sb, textRenderer);
        textRenderer.render(EOL_STR, sb, "Text");
        this.formatElements(sb, Strings.EMPTY, 0, this.throwable.getStackTrace(), this.extendedStackTrace,
            ignorePackages, textRenderer, suffix);
        return sb.toString();
    }
