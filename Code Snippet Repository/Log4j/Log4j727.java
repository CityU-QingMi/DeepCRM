    @Override
    public ReadOnlyStringMap convertToEntityAttribute(final String s) {
        if (Strings.isEmpty(s)) {
            return null;
        }
        try {
            final StringMap result = ContextDataFactory.createContextData();
            final ObjectNode root = (ObjectNode) OBJECT_MAPPER.readTree(s);
            final Iterator<Map.Entry<String, JsonNode>> entries = root.fields();
            while (entries.hasNext()) {
                final Map.Entry<String, JsonNode> entry = entries.next();

                // Don't know what to do with non-text values.
                // Maybe users who need this need to provide custom converter?
                final Object value = entry.getValue().textValue();
                result.putValue(entry.getKey(), value);
            }
            return result;
        } catch (final IOException e) {
            throw new PersistenceException("Failed to convert JSON string to map.", e);
        }
    }
