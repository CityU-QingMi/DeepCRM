    public PatternProcessor(final String pattern) {
        this.pattern = pattern;
        final PatternParser parser = createPatternParser();
        final List<PatternConverter> converters = new ArrayList<>();
        final List<FormattingInfo> fields = new ArrayList<>();
        parser.parse(pattern, converters, fields, false, false, false);
        final FormattingInfo[] infoArray = new FormattingInfo[fields.size()];
        patternFields = fields.toArray(infoArray);
        final ArrayPatternConverter[] converterArray = new ArrayPatternConverter[converters.size()];
        patternConverters = converters.toArray(converterArray);

        for (final ArrayPatternConverter converter : patternConverters) {
            if (converter instanceof DatePatternConverter) {
                final DatePatternConverter dateConverter = (DatePatternConverter) converter;
                frequency = calculateFrequency(dateConverter.getPattern());
            }
        }
    }
