    @Override
    public Map<String, String> deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        final List<MapEntry> list = jp.readValueAs(new TypeReference<List<MapEntry>>() {
            // empty
        });
        final HashMap<String, String> map = new HashMap<>(list.size());
        for (final MapEntry mapEntry : list) {
            map.put(mapEntry.getKey(), mapEntry.getValue());
        }
        return map;
    }
