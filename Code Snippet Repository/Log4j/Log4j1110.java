    protected Result filter(final StructuredDataMessage message) {
        boolean match = false;
        final IndexedReadOnlyStringMap map = getStringMap();
        for (int i = 0; i < map.size(); i++) {
            final StringBuilder toMatch = getValue(message, map.getKeyAt(i));
            if (toMatch != null) {
                match = listContainsValue((List<String>) map.getValueAt(i), toMatch);
            } else {
                match = false;
            }
            if ((!isAnd() && match) || (isAnd() && !match)) {
                break;
            }
        }
        return match ? onMatch : onMismatch;
    }
