	@Test
	public void shouldPrintAndParseISOTime() throws Exception {
		DateFormatter formatter = new DateFormatter();
		formatter.setTimeZone(UTC);
		formatter.setIso(ISO.TIME);
		Date date = getDate(2009, Calendar.JANUARY, 1, 14, 23, 5, 3);
		assertThat(formatter.print(date, Locale.US), is("14:23:05.003Z"));
		assertThat(formatter.parse("14:23:05.003Z", Locale.US),
				is(getDate(1970, Calendar.JANUARY, 1, 14, 23, 5, 3)));
	}
