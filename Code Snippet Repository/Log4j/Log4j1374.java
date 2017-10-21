    @Override
    @PerformanceSensitive("")
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final int start = toAppendTo.length();
        for (int i = 0; i < formatters.size(); i++) {
            final PatternFormatter formatter = formatters.get(i);
            formatter.format(event, toAppendTo);
        }
        if (toAppendTo.length() > start) {
            toAppendTo.insert(start, style);
            toAppendTo.append(AnsiEscape.getDefaultStyle());
        }
    }
