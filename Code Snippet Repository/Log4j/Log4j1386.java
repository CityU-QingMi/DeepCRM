    void parseSubstitution(final LogEvent event, final StringBuilder substitutionBuffer) {
        if (substitutionFormatters != null) {
            for (int i = 0; i < substitutionFormatters.size(); i++) {
                final PatternFormatter formatter = substitutionFormatters.get(i);
                formatter.format(event, substitutionBuffer);
            }
        } else {
            substitutionBuffer.append(substitution);
        }
    }
