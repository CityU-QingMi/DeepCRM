    private void appendMap(final String prefix, final Map<String, String> map, final StringBuilder sb,
            final ListChecker checker) {
        final SortedMap<String, String> sorted = new TreeMap<>(map);
        for (final Map.Entry<String, String> entry : sorted.entrySet()) {
            if (checker.check(entry.getKey()) && entry.getValue() != null) {
                sb.append(' ');
                if (prefix != null) {
                    sb.append(prefix);
                }
                final String safeKey = escapeNewlines(escapeSDParams(entry.getKey()), escapeNewLine);
                final String safeValue = escapeNewlines(escapeSDParams(entry.getValue()), escapeNewLine);
                StringBuilders.appendKeyDqValue(sb, safeKey, safeValue);
            }
        }
    }
