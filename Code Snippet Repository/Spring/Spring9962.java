	@Test
	public void acceptLanguage() {
		String headerValue = "fr-ch, fr;q=0.9, en-*;q=0.8, de;q=0.7, *;q=0.5";
		headers.setAcceptLanguage(Locale.LanguageRange.parse(headerValue));
		assertEquals(headerValue, headers.getFirst(HttpHeaders.ACCEPT_LANGUAGE));

		List<Locale.LanguageRange> expectedRanges = Arrays.asList(
				new Locale.LanguageRange("fr-ch"),
				new Locale.LanguageRange("fr", 0.9),
				new Locale.LanguageRange("en-*", 0.8),
				new Locale.LanguageRange("de", 0.7),
				new Locale.LanguageRange("*", 0.5)
		);
		assertEquals(expectedRanges, headers.getAcceptLanguage());
		assertEquals(Locale.forLanguageTag("fr-ch"), headers.getAcceptLanguageAsLocales().get(0));

		headers.setAcceptLanguageAsLocales(Collections.singletonList(Locale.FRANCE));
		assertEquals(Locale.FRANCE, headers.getAcceptLanguageAsLocales().get(0));
	}
