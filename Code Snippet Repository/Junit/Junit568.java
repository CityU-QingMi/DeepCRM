	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setDelimiter(delimiter);
		settings.getFormat().setQuote('\'');
		settings.getFormat().setQuoteEscape('\'');
		settings.setEmptyValue("");
		settings.setAutoConfigurationEnabled(false);
		CsvParser csvParser = new CsvParser(settings);
		AtomicLong index = new AtomicLong(0);
		// @formatter:off
		return Arrays.stream(lines)
				.map(line ->
						Preconditions.notNull(csvParser.parseLine(line),
								() -> "Line at index " + index.get() + " contains invalid CSV: \"" + line + "\""))
				.peek(values -> index.incrementAndGet())
				.map(Arguments::of);
		// @formatter:on
	}
