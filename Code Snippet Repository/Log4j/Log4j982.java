    public DefaultScriptComponentBuilder(final DefaultConfigurationBuilder<? extends Configuration> builder,
                                         final String name, final String language, final String text) {
        super(builder, name, "Script");
        if (language != null) {
            addAttribute("language", language);
        }
        if (text != null) {
            addAttribute("text", text);
        }
    }
