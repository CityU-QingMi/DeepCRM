        @Override
        public PluginEntry visitType(final TypeElement e, final Plugin plugin) {
            Objects.requireNonNull(plugin, "Plugin annotation is null.");
            final PluginEntry entry = new PluginEntry();
            entry.setKey(plugin.name().toLowerCase(Locale.US));
            entry.setClassName(elements.getBinaryName(e).toString());
            entry.setName(Plugin.EMPTY.equals(plugin.elementType()) ? plugin.name() : plugin.elementType());
            entry.setPrintable(plugin.printObject());
            entry.setDefer(plugin.deferChildren());
            entry.setCategory(plugin.category());
            return entry;
        }
