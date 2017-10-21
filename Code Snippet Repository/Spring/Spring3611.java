	private void testJodaStylePatterns(String style, Locale locale, Date date) throws Exception {
		DateFormatter formatter = new DateFormatter();
		formatter.setTimeZone(UTC);
		formatter.setStylePattern(style);
		DateTimeFormatter jodaFormatter = DateTimeFormat.forStyle(style).withLocale(locale).withZone(DateTimeZone.UTC);
		String jodaPrinted = jodaFormatter.print(date.getTime());
		assertThat("Unable to print style pattern " + style,
				formatter.print(date, locale), is(equalTo(jodaPrinted)));
		assertThat("Unable to parse style pattern " + style,
				formatter.parse(jodaPrinted, locale), is(equalTo(date)));
	}
