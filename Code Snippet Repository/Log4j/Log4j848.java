        @Override
        public Routes build() {
            if (routes == null || routes.length == 0) {
                LOGGER.error("No Routes configured.");
                return null;
            }
            if (patternScript != null && pattern != null) {
                LOGGER.warn("In a Routes element, you must configure either a Script element or a pattern attribute.");
            }
            if (patternScript != null) {
                if (configuration == null) {
                    LOGGER.error("No Configuration defined for Routes; required for Script");
                } else {
                    configuration.getScriptManager().addScript(patternScript);
                }
            }
            return new Routes(configuration, patternScript, pattern, routes);
        }
