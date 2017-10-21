    @Override
    public StringMap getReadOnlyContextData() {
        final Map<String, String> copy = getCopy();
        if (copy.isEmpty()) {
            return EMPTY_CONTEXT_DATA;
        }
        final StringMap result = new SortedArrayStringMap();
        for (final Entry<String, String> entry : copy.entrySet()) {
            result.putValue(entry.getKey(), entry.getValue());
        }
        return result;
    }
