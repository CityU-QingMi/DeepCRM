    public Object execute(final String name, final Bindings bindings) {
        final ScriptRunner scriptRunner = scriptRunners.get(name);
        if (scriptRunner == null) {
            logger.warn("No script named {} could be found");
            return null;
        }
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                return scriptRunner.execute(bindings);
            }
        });
    }
