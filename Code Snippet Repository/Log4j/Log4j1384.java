    protected EqualsBaseReplacementConverter(final String name, final String style,
                                            final List<PatternFormatter> formatters, final String testString,
                                            final String substitution, final PatternParser parser) {
        super(name, style);
        this.testString = testString;
        this.substitution = substitution;
        this.formatters = formatters;

        // check if substitution needs to be parsed
        substitutionFormatters = substitution.contains("%") ? parser.parse(substitution) : null;
    }
