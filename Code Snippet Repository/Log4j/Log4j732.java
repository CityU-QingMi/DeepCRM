    @Override
    public ThreadContext.ContextStack convertToEntityAttribute(final String s) {
        if (Strings.isEmpty(s)) {
            return null;
        }

        List<String> list;
        try {
            list = ContextMapJsonAttributeConverter.OBJECT_MAPPER.readValue(s, new TypeReference<List<String>>() { });
        } catch (final IOException e) {
            throw new PersistenceException("Failed to convert JSON string to list for stack.", e);
        }

        final DefaultThreadContextStack result = new DefaultThreadContextStack(true);
        result.addAll(list);
        return result;
    }
