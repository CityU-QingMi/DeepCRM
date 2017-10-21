    public String getPattern(final LogEvent event, final ConcurrentMap<Object, Object> scriptStaticVariables) {
        if (patternScript != null) {
            final ScriptManager scriptManager = configuration.getScriptManager();
            final Bindings bindings = scriptManager.createBindings(patternScript);
            bindings.put(STATIC_VARIABLES_KEY, scriptStaticVariables);
            bindings.put(LOG_EVENT_KEY, event);
            final Object object = scriptManager.execute(patternScript.getName(), bindings);
            bindings.remove(LOG_EVENT_KEY);
            return Objects.toString(object, null);
        }
        return pattern;
    }
