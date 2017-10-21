    private static void appendSelectedKeys(final String[] keys, final ReadOnlyStringMap contextData, final StringBuilder toAppendTo) {
        // Print all the keys in the array that have a value.
        final StringBuilder sb = getStringBuilder();
        sb.append("{");
        for (int i = 0; i < keys.length; i++) {
            final String theKey = keys[i];
            final Object value = contextData.getValue(theKey);
            if (value != null) { // !contextData.containskey(theKey)
                if (sb.length() > 1) {
                    sb.append(", ");
                }
                sb.append(theKey).append('=');
                StringBuilders.appendValue(sb, value);
            }
        }
        sb.append('}');
        toAppendTo.append(sb);
        trimToMaxSize(sb);
    }
