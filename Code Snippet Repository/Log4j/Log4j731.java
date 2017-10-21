    @Override
    public String convertToDatabaseColumn(final ThreadContext.ContextStack contextStack) {
        if (contextStack == null) {
            return null;
        }

        try {
            return ContextMapJsonAttributeConverter.OBJECT_MAPPER.writeValueAsString(contextStack.asList());
        } catch (final IOException e) {
            throw new PersistenceException("Failed to convert stack list to JSON string.", e);
        }
    }
