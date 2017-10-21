	@Test
	public void shouldPrintAndParseISODate() throws Exception {
		DateFormatter formatter = new DateFormatter();
		formatter.setTimeZone(UTC);
		formatter.setIso(ISO.DATE);
		Date date = getDate(2009, Calendar.JUNE, 1, 14, 23, 5, 3);
		assertThat(formatter.print(date, Locale.US), is("2009-06-01"));
		assertThat(formatter.parse("2009-6-01", Locale.US),
				is(getDate(2009, Calendar.JUNE, 1)));
	}
