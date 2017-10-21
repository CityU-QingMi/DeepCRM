    protected static CSVFormat createFormat(final String format, final Character delimiter, final Character escape,
            final Character quote, final QuoteMode quoteMode, final String nullString, final String recordSeparator) {
        CSVFormat csvFormat = CSVFormat.valueOf(format);
        if (isNotNul(delimiter)) {
            csvFormat = csvFormat.withDelimiter(delimiter);
        }
        if (isNotNul(escape)) {
            csvFormat = csvFormat.withEscape(escape);
        }
        if (isNotNul(quote)) {
            csvFormat = csvFormat.withQuote(quote);
        }
        if (quoteMode != null) {
            csvFormat = csvFormat.withQuoteMode(quoteMode);
        }
        if (nullString != null) {
            csvFormat = csvFormat.withNullString(nullString);
        }
        if (recordSeparator != null) {
            csvFormat = csvFormat.withRecordSeparator(recordSeparator);
        }
        return csvFormat;
    }
