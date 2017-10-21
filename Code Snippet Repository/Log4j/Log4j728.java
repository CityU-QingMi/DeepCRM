    @Override
    public String convertToDatabaseColumn(final Map<String, String> contextMap) {
        if (contextMap == null) {
            return null;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(contextMap);
        } catch (final IOException e) {
            throw new PersistenceException("Failed to convert map to JSON string.", e);
        }
    }
