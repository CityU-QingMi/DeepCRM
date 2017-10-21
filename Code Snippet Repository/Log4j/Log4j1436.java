    public ScriptManager(final Configuration configuration, final WatchManager watchManager) {
        this.configuration = configuration;
        this.watchManager = watchManager;
        final List<ScriptEngineFactory> factories = manager.getEngineFactories();
        if (logger.isDebugEnabled()) {
            final StringBuilder sb = new StringBuilder();
            final int factorySize = factories.size();
            logger.debug("Installed {} script engine{}", factorySize, factorySize != 1 ? "s" : Strings.EMPTY);
            for (final ScriptEngineFactory factory : factories) {
                String threading = (String) factory.getParameter(KEY_THREADING);
                if (threading == null) {
                    threading = "Not Thread Safe";
                }
                final StringBuilder names = new StringBuilder();
                final List<String> languageNames = factory.getNames();
                for (final String name : languageNames) {
                    if (names.length() > 0) {
                        names.append(", ");
                    }
                    names.append(name);
                }
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(names);
                final boolean compiled = factory.getScriptEngine() instanceof Compilable;
                logger.debug("{} version: {}, language: {}, threading: {}, compile: {}, names: {}, factory class: {}",
                        factory.getEngineName(), factory.getEngineVersion(), factory.getLanguageName(), threading,
                        compiled, languageNames, factory.getClass().getName());
            }
            languages = sb.toString();
        } else {
            final StringBuilder names = new StringBuilder();
            for (final ScriptEngineFactory factory : factories) {
                for (final String name : factory.getNames()) {
                    if (names.length() > 0) {
                        names.append(", ");
                    }
                    names.append(name);
                }
            }
            languages = names.toString();
        }
    }
