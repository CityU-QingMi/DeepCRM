    private StringBuilder getValue(final StructuredDataMessage data, final String key) {
        final StringBuilder sb = getStringBuilder();
        if (key.equalsIgnoreCase("id")) {
            data.getId().formatTo(sb);
            return sb;
        } else if (key.equalsIgnoreCase("id.name")) {
            return appendOrNull(data.getId().getName(), sb);
        } else if (key.equalsIgnoreCase("type")) {
            return appendOrNull(data.getType(), sb);
        } else if (key.equalsIgnoreCase("message")) {
            data.formatTo(sb);
            return sb;
        } else {
            return appendOrNull(data.get(key), sb);
        }
    }
