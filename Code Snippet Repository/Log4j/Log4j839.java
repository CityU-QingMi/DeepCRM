    @SuppressWarnings("")
    public List<PathWithAttributes> selectFilesToDelete(final Path basePath, final List<PathWithAttributes> candidates) {
        final SimpleBindings bindings = new SimpleBindings();
        bindings.put("basePath", basePath);
        bindings.put("pathList", candidates);
        bindings.putAll(configuration.getProperties());
        bindings.put("configuration", configuration);
        bindings.put("substitutor", configuration.getStrSubstitutor());
        bindings.put("statusLogger", LOGGER);
        final Object object = configuration.getScriptManager().execute(script.getName(), bindings);
        return (List<PathWithAttributes>) object;
    }
