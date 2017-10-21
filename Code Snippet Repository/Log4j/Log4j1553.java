    public static Object getFieldValue(final Field field, final Object instance) {
        makeAccessible(field);
        if (!Modifier.isStatic(field.getModifiers())) {
            Objects.requireNonNull(instance, "No instance given for non-static field");
        }
        try {
            return field.get(instance);
        } catch (final IllegalAccessException e) {
            throw new UnsupportedOperationException(e);
        }
    }
