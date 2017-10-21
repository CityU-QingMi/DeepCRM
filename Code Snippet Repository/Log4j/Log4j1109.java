    @PluginFactory
    public static ScriptFilter createFilter(
            @PluginElement("Script") final AbstractScript script,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch,
            @PluginConfiguration final Configuration configuration) {

        if (script == null) {
            LOGGER.error("A Script, ScriptFile or ScriptRef element must be provided for this ScriptFilter");
            return null;
        }
        if (script instanceof ScriptRef) {
            if (configuration.getScriptManager().getScript(script.getName()) == null) {
                logger.error("No script with name {} has been declared.", script.getName());
                return null;
            }
        }

        return new ScriptFilter(script, configuration, match, mismatch);
    }
