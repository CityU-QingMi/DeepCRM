    @PluginFactory
    public static Script createScript(
            // @formatter:off
            @PluginAttribute("name") final String name,
            @PluginAttribute("language") String language,
            @PluginValue("scriptText") final String scriptText) {
            // @formatter:on
        if (language == null) {
            LOGGER.info("No script language supplied, defaulting to {}", DEFAULT_LANGUAGE);
            language = DEFAULT_LANGUAGE;
        }
        if (scriptText == null) {
            LOGGER.error("No scriptText attribute provided for ScriptFile {}", name);
            return null;
        }
        return new Script(name, language, scriptText);

    }
