    @PluginFactory
    public static ScriptRef createReference(
            // @formatter:off
            @PluginAttribute("ref") final String name,
            @PluginConfiguration final Configuration configuration) {
            // @formatter:on
        if (name == null) {
            LOGGER.error("No script name provided");
            return null;
        }
        return new ScriptRef(name, configuration.getScriptManager());

    }
