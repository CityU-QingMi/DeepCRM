    @PluginFactory
    public static ScriptCondition createCondition(@PluginElement("Script") final AbstractScript script,
            @PluginConfiguration final Configuration configuration) {

        if (script == null) {
            LOGGER.error("A Script, ScriptFile or ScriptRef element must be provided for this ScriptCondition");
            return null;
        }
        if (script instanceof ScriptRef) {
            if (configuration.getScriptManager().getScript(script.getName()) == null) {
                LOGGER.error("ScriptCondition: No script with name {} has been declared.", script.getName());
                return null;
            }
        }
        return new ScriptCondition(script, configuration);
    }
