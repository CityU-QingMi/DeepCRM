    @SuppressWarnings("")
    public void formatWrapper(final StringBuilder sb, final ThrowableProxy cause, final List<String> ignorePackages,
                              final TextRenderer textRenderer, final String suffix) {
        final Throwable caused = cause.getCauseProxy() != null ? cause.getCauseProxy().getThrowable() : null;
        if (caused != null) {
            this.formatWrapper(sb, cause.causeProxy, ignorePackages, textRenderer, suffix);
            sb.append(WRAPPED_BY_LABEL);
            renderSuffix(suffix, sb, textRenderer);
        }
        cause.renderOn(sb, textRenderer);
        renderSuffix(suffix, sb, textRenderer);
        textRenderer.render(EOL_STR, sb, "Text");
        this.formatElements(sb, Strings.EMPTY, cause.commonElementCount,
            cause.getThrowable().getStackTrace(), cause.extendedStackTrace, ignorePackages, textRenderer, suffix);
    }
