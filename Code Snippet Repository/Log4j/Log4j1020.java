    public Map<String, List<PluginType<?>>> loadFromMainClassLoader() {
        final Map<String, List<PluginType<?>>> existing = pluginsByCategoryRef.get();
        if (existing != null) {
            // already loaded
            return existing;
        }
        final Map<String, List<PluginType<?>>> newPluginsByCategory = decodeCacheFiles(Loader.getClassLoader());

        // Note multiple threads could be calling this method concurrently. Both will do the work,
        // but only one will be allowed to store the result in the AtomicReference.
        // Return the map produced by whichever thread won the race, so all callers will get the same result.
        if (pluginsByCategoryRef.compareAndSet(null, newPluginsByCategory)) {
            return newPluginsByCategory;
        }
        return pluginsByCategoryRef.get();
    }
