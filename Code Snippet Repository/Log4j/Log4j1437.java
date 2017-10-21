    public void addScript(final AbstractScript script) {
        final ScriptEngine engine = manager.getEngineByName(script.getLanguage());
        if (engine == null) {
            logger.error("No ScriptEngine found for language " + script.getLanguage() + ". Available languages are: "
                    + languages);
            return;
        }
        if (engine.getFactory().getParameter(KEY_THREADING) == null) {
            scriptRunners.put(script.getName(), new ThreadLocalScriptRunner(script));
        } else {
            scriptRunners.put(script.getName(), new MainScriptRunner(engine, script));
        }

        if (script instanceof ScriptFile) {
            final ScriptFile scriptFile = (ScriptFile) script;
            final Path path = scriptFile.getPath();
            if (scriptFile.isWatched() && path != null) {
                watchManager.watchFile(path.toFile(), this);
            }
        }
    }
