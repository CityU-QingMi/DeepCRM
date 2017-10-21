    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder(map.size() * 13);
        result.append('{');
        final String[] keys = getSortedKeys();
        for (int i = 0; i < keys.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(keys[i]).append('=').append(map.get(keys[i]));
        }
        result.append('}');
        return result.toString();
    }
