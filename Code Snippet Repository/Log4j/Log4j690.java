        @Override
        public Appender build() {
            if (name == null) {
                LOGGER.error("Name missing.");
                return null;
            }
            if (script == null) {
                LOGGER.error("Script missing for ScriptAppenderSelector appender {}", name);
                return null;
            }
            if (appenderSet == null) {
                LOGGER.error("AppenderSet missing for ScriptAppenderSelector appender {}", name);
                return null;
            }
            if (configuration == null) {
                LOGGER.error("Configuration missing for ScriptAppenderSelector appender {}", name);
                return null;
            }
            final ScriptManager scriptManager = configuration.getScriptManager();
            scriptManager.addScript(script);
            final Bindings bindings = scriptManager.createBindings(script);
            final Object object = scriptManager.execute(script.getName(), bindings);
            final String appenderName = Objects.toString(object, null);
            final Appender appender = appenderSet.createAppender(appenderName, name);
            return appender;
        }
