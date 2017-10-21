    public Map<String, List<PluginType<?>>> loadFromBundle(final long bundleId, final ClassLoader loader) {
        Map<String, List<PluginType<?>>> existing = pluginsByCategoryByBundleId.get(bundleId);
        if (existing != null) {
            // already loaded from this classloader
            return existing;
        }
        final Map<String, List<PluginType<?>>> newPluginsByCategory = decodeCacheFiles(loader);

        // Note multiple threads could be calling this method concurrently. Both will do the work,
        // but only one will be allowed to store the result in the outer map.
        // Return the inner map produced by whichever thread won the race, so all callers will get the same result.
        existing = pluginsByCategoryByBundleId.putIfAbsent(bundleId, newPluginsByCategory);
        if (existing != null) {
            return existing;
        }
        return newPluginsByCategory;
    }
