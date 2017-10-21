    @Override
    public void fileModified(final File file) {
        final ScriptRunner runner = scriptRunners.get(file.toString());
        if (runner == null) {
            logger.info("{} is not a running script");
            return;
        }
        final ScriptEngine engine = runner.getScriptEngine();
        final AbstractScript script = runner.getScript();
        if (engine.getFactory().getParameter(KEY_THREADING) == null) {
            scriptRunners.put(script.getName(), new ThreadLocalScriptRunner(script));
        } else {
            scriptRunners.put(script.getName(), new MainScriptRunner(engine, script));
        }

    }
