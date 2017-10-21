	@Override
	public void accept(CsvFileSource annotation) {
		resources = annotation.resources();
		charset = Charset.forName(annotation.encoding());
		settings = new CsvParserSettings();
		settings.getFormat().setDelimiter(annotation.delimiter());
		settings.getFormat().setLineSeparator(annotation.lineSeparator());
		settings.getFormat().setQuote('"');
		settings.getFormat().setQuoteEscape('"');
		settings.setEmptyValue("");
		settings.setAutoConfigurationEnabled(false);
	}
