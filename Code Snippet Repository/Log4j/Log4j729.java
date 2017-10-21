    @Override
    public Map<String, String> convertToEntityAttribute(final String s) {
        if (Strings.isEmpty(s)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(s, new TypeReference<Map<String, String>>() { });
        } catch (final IOException e) {
            throw new PersistenceException("Failed to convert JSON string to map.", e);
        }
    }
