        @Override
        public ScriptPatternSelector build() {
            if (script == null) {
                LOGGER.error("A Script, ScriptFile or ScriptRef element must be provided for this ScriptFilter");
                return null;
            }
            if (script instanceof ScriptRef) {
                if (configuration.getScriptManager().getScript(script.getName()) == null) {
                    LOGGER.error("No script with name {} has been declared.", script.getName());
                    return null;
                }
            }
            if (defaultPattern == null) {
                defaultPattern = PatternLayout.DEFAULT_CONVERSION_PATTERN;
            }
            if (properties == null || properties.length == 0) {
                LOGGER.warn("No marker patterns were provided");
                return null;
            }
            return new ScriptPatternSelector(script, properties, defaultPattern, alwaysWriteExceptions, disableAnsi,
                    noConsoleNoAnsi, configuration);
        }
