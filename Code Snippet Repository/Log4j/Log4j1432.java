    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final int start = toAppendTo.length();
        boolean allVarsEmpty = true;
        boolean hasVars = false;
        for (int i = 0; i < formatters.size(); i++) {
            final PatternFormatter formatter = formatters.get(i);
            final int formatterStart = toAppendTo.length();
            formatter.format(event, toAppendTo);
            if (formatter.getConverter().isVariable()) {
                hasVars = true;
                allVarsEmpty = allVarsEmpty && (toAppendTo.length() == formatterStart);
            }
        }
        if (!hasVars || allVarsEmpty) {
            toAppendTo.setLength(start); // remove formatter results
        }
    }
