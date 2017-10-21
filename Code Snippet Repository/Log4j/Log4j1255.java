    private Map<String, FieldFormatter> createFieldFormatters(final LoggerFields[] loggerFields,
            final Configuration config) {
        final Map<String, FieldFormatter> sdIdMap = new HashMap<>(loggerFields == null ? 0 : loggerFields.length);
        if (loggerFields != null) {
            for (final LoggerFields loggerField : loggerFields) {
                final StructuredDataId key = loggerField.getSdId() == null ? mdcSdId : loggerField.getSdId();
                final Map<String, List<PatternFormatter>> sdParams = new HashMap<>();
                final Map<String, String> fields = loggerField.getMap();
                if (!fields.isEmpty()) {
                    final PatternParser fieldParser = createPatternParser(config, null);

                    for (final Map.Entry<String, String> entry : fields.entrySet()) {
                        final List<PatternFormatter> formatters = fieldParser.parse(entry.getValue());
                        sdParams.put(entry.getKey(), formatters);
                    }
                    final FieldFormatter fieldFormatter = new FieldFormatter(sdParams,
                            loggerField.getDiscardIfAllFieldsAreEmpty());
                    sdIdMap.put(key.toString(), fieldFormatter);
                }
            }
        }
        return sdIdMap.size() > 0 ? sdIdMap : null;
    }
