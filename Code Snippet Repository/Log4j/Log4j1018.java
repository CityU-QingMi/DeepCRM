    private static void mergeByName(final Map<String, PluginType<?>> newPlugins, final List<PluginType<?>> plugins) {
        if (plugins == null) {
            return;
        }
        for (final PluginType<?> pluginType : plugins) {
            final String key = pluginType.getKey();
            final PluginType<?> existing = newPlugins.get(key);
            if (existing == null) {
                newPlugins.put(key, pluginType);
            } else if (!existing.getPluginClass().equals(pluginType.getPluginClass())) {
                LOGGER.warn("Plugin [{}] is already mapped to {}, ignoring {}",
                    key, existing.getPluginClass(), pluginType.getPluginClass());
            }
        }
    }
