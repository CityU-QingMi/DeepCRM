    public static void setFieldValue(final Field field, final Object instance, final Object value) {
        makeAccessible(field);
        if (!Modifier.isStatic(field.getModifiers())) {
            Objects.requireNonNull(instance, "No instance given for non-static field");
        }
        try {
            field.set(instance, value);
        } catch (final IllegalAccessException e) {
            throw new UnsupportedOperationException(e);
        }
    }
