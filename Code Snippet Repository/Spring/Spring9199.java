	public void setAcceptLanguage(List<Locale.LanguageRange> languages) {
		Assert.notNull(languages, "'languages' must not be null");
		DecimalFormat decimal = new DecimalFormat("0.0", DECIMAL_FORMAT_SYMBOLS);
		List<String> values = languages.stream()
				.map(range ->
						range.getWeight() == Locale.LanguageRange.MAX_WEIGHT ?
								range.getRange() :
								range.getRange() + ";q=" + decimal.format(range.getWeight()))
				.collect(Collectors.toList());
		set(ACCEPT_LANGUAGE, toCommaDelimitedString(values));
	}
