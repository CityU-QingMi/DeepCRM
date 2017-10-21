    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final int initialSize = toAppendTo.length();
        for (int i = 0; i < formatters.size(); i++) {
            final PatternFormatter formatter = formatters.get(i);
            formatter.format(event, toAppendTo);
        }
        if (equals(testString, toAppendTo, initialSize, toAppendTo.length() - initialSize)) {
            toAppendTo.setLength(initialSize);
            parseSubstitution(event, toAppendTo);
        }
    }
