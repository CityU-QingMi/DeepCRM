        @Override
        public Object execute(final Bindings bindings) {
            if (compiledScript != null) {
                try {
                    return compiledScript.eval(bindings);
                } catch (final ScriptException ex) {
                    logger.error("Error running script " + script.getName(), ex);
                    return null;
                }
            }
            try {
                return scriptEngine.eval(script.getScriptText(), bindings);
            } catch (final ScriptException ex) {
                logger.error("Error running script " + script.getName(), ex);
                return null;
            }
        }
