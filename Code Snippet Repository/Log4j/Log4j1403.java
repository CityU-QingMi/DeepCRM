    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final int initialLength = toAppendTo.length();
        for (int i = 0; i < formatters.size(); i++) {
            final PatternFormatter formatter = formatters.get(i);
            formatter.format(event, toAppendTo);
            if (toAppendTo.length() > initialLength + maxLength) {        // stop early
                break;
            }
        }
        if (toAppendTo.length() > initialLength + maxLength) {
            toAppendTo.setLength(initialLength + maxLength);
            if (maxLength > 20) {        // only append ellipses if length is not very short
                toAppendTo.append("...");
            }
        }
    }
