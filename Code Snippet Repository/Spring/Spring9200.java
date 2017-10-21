	public List<Locale> getAcceptLanguageAsLocales() {
		List<Locale.LanguageRange> ranges = getAcceptLanguage();
		if (ranges.isEmpty()) {
			return Collections.emptyList();
		}
		return ranges.stream()
				.map(range -> Locale.forLanguageTag(range.getRange()))
				.filter(locale -> StringUtils.hasText(locale.getDisplayName()))
				.collect(Collectors.toList());
	}
