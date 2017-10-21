    private TextRenderer loadMessageRenderer(final String[] options) {
        if (options != null) {
            for (final String option : options) {
                switch (option.toUpperCase(Locale.ROOT)) {
                case "ANSI":
                    if (Loader.isJansiAvailable()) {
                        return new JAnsiTextRenderer(options, JAnsiTextRenderer.DefaultMessageStyleMap);
                    }
                    StatusLogger.getLogger()
                            .warn("You requested ANSI message rendering but JANSI is not on the classpath.");
                    return null;
                case "HTML":
                    return new HtmlTextRenderer(options);
                }
            }
        }
        return null;
    }
