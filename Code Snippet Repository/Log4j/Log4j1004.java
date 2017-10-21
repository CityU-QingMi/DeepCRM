    public void writeCache(final OutputStream os) throws IOException {
        try (final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(os))) {
            // See PluginManager.readFromCacheFiles for the corresponding decoder. Format may not be changed
            // without breaking existing Log4j2Plugins.dat files.
            out.writeInt(categories.size());
            for (final Map.Entry<String, Map<String, PluginEntry>> category : categories.entrySet()) {
                out.writeUTF(category.getKey());
                final Map<String, PluginEntry> m = category.getValue();
                out.writeInt(m.size());
                for (final Map.Entry<String, PluginEntry> entry : m.entrySet()) {
                    final PluginEntry plugin = entry.getValue();
                    out.writeUTF(plugin.getKey());
                    out.writeUTF(plugin.getClassName());
                    out.writeUTF(plugin.getName());
                    out.writeBoolean(plugin.isPrintable());
                    out.writeBoolean(plugin.isDefer());
                }
            }
        }
    }
