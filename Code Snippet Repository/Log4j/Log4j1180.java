    @Override
    public StringMap deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        final List<MapEntry> list = jp.readValueAs(new TypeReference<List<MapEntry>>() {
            // empty
        });
        final StringMap contextData = new ContextDataFactory().createContextData();
        for (final MapEntry mapEntry : list) {
            contextData.putValue(mapEntry.getKey(), mapEntry.getValue());
        }
        return contextData;
    }
