    private Map<String, List<PluginType<?>>> decodeCacheFiles(final ClassLoader loader) {
        final long startTime = System.nanoTime();
        final PluginCache cache = new PluginCache();
        try {
            final Enumeration<URL> resources = loader.getResources(PluginProcessor.PLUGIN_CACHE_FILE);
            if (resources == null) {
                LOGGER.info("Plugin preloads not available from class loader {}", loader);
            } else {
                cache.loadCacheFiles(resources);
            }
        } catch (final IOException ioe) {
            LOGGER.warn("Unable to preload plugins", ioe);
        }
        final Map<String, List<PluginType<?>>> newPluginsByCategory = new HashMap<>();
        int pluginCount = 0;
        for (final Map.Entry<String, Map<String, PluginEntry>> outer : cache.getAllCategories().entrySet()) {
            final String categoryLowerCase = outer.getKey();
            final List<PluginType<?>> types = new ArrayList<>(outer.getValue().size());
            newPluginsByCategory.put(categoryLowerCase, types);
            for (final Map.Entry<String, PluginEntry> inner : outer.getValue().entrySet()) {
                final PluginEntry entry = inner.getValue();
                final String className = entry.getClassName();
                try {
                    final Class<?> clazz = loader.loadClass(className);
                    final PluginType<?> type = new PluginType<>(entry, clazz, entry.getName());
                    types.add(type);
                    ++pluginCount;
                } catch (final ClassNotFoundException e) {
                    LOGGER.info("Plugin [{}] could not be loaded due to missing classes.", className, e);
                } catch (final VerifyError e) {
                    LOGGER.info("Plugin [{}] could not be loaded due to verification error.", className, e);
                }
            }
        }

        final long endTime = System.nanoTime();
        final DecimalFormat numFormat = new DecimalFormat("#0.000000");
        final double seconds = (endTime - startTime) * 1e-9;
        LOGGER.debug("Took {} seconds to load {} plugins from {}",
            numFormat.format(seconds), pluginCount, loader);
        return newPluginsByCategory;
    }
