    @Override
    public void serialize(final ReadOnlyStringMap contextData, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException, JsonGenerationException {

        final MapEntry[] pairs = new MapEntry[contextData.size()];
        contextData.forEach(new BiConsumer<String, Object>() {
            int i = 0;

            @Override
            public void accept(final String key, final Object value) {
                pairs[i++] = new MapEntry(key, String.valueOf(value));
            }
        });
        jgen.writeObject(pairs);
    }
