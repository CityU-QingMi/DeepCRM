    public void loadCacheFiles(final Enumeration<URL> resources) throws IOException {
        categories.clear();
        while (resources.hasMoreElements()) {
            final URL url = resources.nextElement();
            try (final DataInputStream in = new DataInputStream(new BufferedInputStream(url.openStream()))) {
                final int count = in.readInt();
                for (int i = 0; i < count; i++) {
                    final String category = in.readUTF();
                    final Map<String, PluginEntry> m = getCategory(category);
                    final int entries = in.readInt();
                    for (int j = 0; j < entries; j++) {
                        final PluginEntry entry = new PluginEntry();
                        entry.setKey(in.readUTF());
                        entry.setClassName(in.readUTF());
                        entry.setName(in.readUTF());
                        entry.setPrintable(in.readBoolean());
                        entry.setDefer(in.readBoolean());
                        entry.setCategory(category);
                        if (!m.containsKey(entry.getKey())) {
                            m.put(entry.getKey(), entry);
                        }
                    }
                }
            }
        }
    }
