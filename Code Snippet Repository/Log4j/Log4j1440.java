        public MainScriptRunner(final ScriptEngine scriptEngine, final AbstractScript script) {
            this.script = script;
            this.scriptEngine = scriptEngine;
            CompiledScript compiled = null;
            if (scriptEngine instanceof Compilable) {
                logger.debug("Script {} is compilable", script.getName());
                compiled = AccessController.doPrivileged(new PrivilegedAction<CompiledScript>() {
                    @Override
                    public CompiledScript run() {
                        try {
                            return ((Compilable) scriptEngine).compile(script.getScriptText());
                        } catch (final Throwable ex) {
/**/
/**/
/**/
/**/
                            logger.warn("Error compiling script", ex);
                            return null;
                        }
                    }
                });
            }
            compiledScript = compiled;
        }
