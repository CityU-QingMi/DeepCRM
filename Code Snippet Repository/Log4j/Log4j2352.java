    private void parse(final String pattern, final boolean convert, final StringBuilder buf, final Date date, final int i) {
        final PatternParser parser0 = new PatternParser(null, "Converter", null);
        final List<PatternConverter> converters = new ArrayList<>();
        final List<FormattingInfo> fields = new ArrayList<>();
        parser0.parse(pattern, converters, fields, false, false, convert);
        final FormattingInfo[] infoArray = new FormattingInfo[fields.size()];
        final FormattingInfo[] patternFields = fields.toArray(infoArray);
        final ArrayPatternConverter[] converterArray = new ArrayPatternConverter[converters.size()];
        final ArrayPatternConverter[] patternConverters = converters.toArray(converterArray);
        formatFileName(patternConverters, patternFields, buf, date, i);
    }
