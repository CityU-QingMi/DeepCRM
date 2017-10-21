    @Override
    public void serialize(final Map<String, String> map, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException, JsonGenerationException {
        final Set<Entry<String, String>> entrySet = map.entrySet();
        final MapEntry[] pairs = new MapEntry[entrySet.size()];
        int i = 0;
        for (final Entry<String, String> entry : entrySet) {
            pairs[i++] = new MapEntry(entry.getKey(), entry.getValue());
        }
        jgen.writeObject(pairs);
    }
