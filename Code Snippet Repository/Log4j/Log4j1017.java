    public void collectPlugins(final List<String> packages) {
        final String categoryLowerCase = category.toLowerCase();
        final Map<String, PluginType<?>> newPlugins = new LinkedHashMap<>();

        // First, iterate the Log4j2Plugin.dat files found in the main CLASSPATH
        Map<String, List<PluginType<?>>> builtInPlugins = PluginRegistry.getInstance().loadFromMainClassLoader();
        if (builtInPlugins.isEmpty()) {
            // If we didn't find any plugins above, someone must have messed with the log4j-core.jar.
            // Search the standard package in the hopes we can find our core plugins.
            builtInPlugins = PluginRegistry.getInstance().loadFromPackage(LOG4J_PACKAGES);
        }
        mergeByName(newPlugins, builtInPlugins.get(categoryLowerCase));

        // Next, iterate any Log4j2Plugin.dat files from OSGi Bundles
        for (final Map<String, List<PluginType<?>>> pluginsByCategory : PluginRegistry.getInstance().getPluginsByCategoryByBundleId().values()) {
            mergeByName(newPlugins, pluginsByCategory.get(categoryLowerCase));
        }

        // Next iterate any packages passed to the static addPackage method.
        for (final String pkg : PACKAGES) {
            mergeByName(newPlugins, PluginRegistry.getInstance().loadFromPackage(pkg).get(categoryLowerCase));
        }
        // Finally iterate any packages provided in the configuration (note these can be changed at runtime).
        if (packages != null) {
            for (final String pkg : packages) {
                mergeByName(newPlugins, PluginRegistry.getInstance().loadFromPackage(pkg).get(categoryLowerCase));
            }
        }

        LOGGER.debug("PluginManager '{}' found {} plugins", category, newPlugins.size());

        plugins = newPlugins;
    }
