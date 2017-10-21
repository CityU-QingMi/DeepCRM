    @Override
    public boolean isValid(final String name, final Object value) {
        if (value instanceof CharSequence) {
            return isValid(name, TypeConverters.convert(value.toString(), Integer.class, -1));
        }
        if (!Integer.class.isInstance(value)) {
            LOGGER.error(annotation.message());
            return false;
        }
        final int port = (int) value;
        if (port < 0 || port > 65535) {
            LOGGER.error(annotation.message());
            return false;
        }
        return true;
    }
