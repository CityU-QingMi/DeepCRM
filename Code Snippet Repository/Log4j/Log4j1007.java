    private void collectPlugins(final Iterable<? extends Element> elements) {
        final Elements elementUtils = processingEnv.getElementUtils();
        final ElementVisitor<PluginEntry, Plugin> pluginVisitor = new PluginElementVisitor(elementUtils);
        final ElementVisitor<Collection<PluginEntry>, Plugin> pluginAliasesVisitor = new PluginAliasesElementVisitor(
                elementUtils);
        for (final Element element : elements) {
            final Plugin plugin = element.getAnnotation(Plugin.class);
            if (plugin == null) {
                continue;
            }
            final PluginEntry entry = element.accept(pluginVisitor, plugin);
            final Map<String, PluginEntry> category = pluginCache.getCategory(entry.getCategory());
            category.put(entry.getKey(), entry);
            final Collection<PluginEntry> entries = element.accept(pluginAliasesVisitor, plugin);
            for (final PluginEntry pluginEntry : entries) {
                category.put(pluginEntry.getKey(), pluginEntry);
            }
        }
    }
