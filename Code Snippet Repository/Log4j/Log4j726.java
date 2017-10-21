    @Override
    public String convertToDatabaseColumn(final ReadOnlyStringMap contextData) {
        if (contextData == null) {
            return null;
        }

        try {
            final JsonNodeFactory factory = OBJECT_MAPPER.getNodeFactory();
            final ObjectNode root = factory.objectNode();
            contextData.forEach(new BiConsumer<String, Object>() {
                @Override
                public void accept(final String key, final Object value) {
                    // we will cheat here and write the toString of the Object... meh, but ok.
                    root.put(key, String.valueOf(value));
                }
            });
            return OBJECT_MAPPER.writeValueAsString(root);
        } catch (final Exception e) {
            throw new PersistenceException("Failed to convert contextData to JSON string.", e);
        }
    }
