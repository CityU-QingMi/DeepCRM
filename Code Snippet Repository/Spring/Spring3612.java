	@Test
	public void shouldUseCorrectOrder() throws Exception {
		DateFormatter formatter = new DateFormatter();
		formatter.setTimeZone(UTC);
		formatter.setStyle(DateFormat.SHORT);
		formatter.setStylePattern("L-");
		formatter.setIso(ISO.DATE_TIME);
		formatter.setPattern("yyyy");
		Date date = getDate(2009, Calendar.JUNE, 1, 14, 23, 5, 3);

		assertThat("uses pattern",formatter.print(date, Locale.US), is("2009"));

		formatter.setPattern("");
		assertThat("uses ISO", formatter.print(date, Locale.US), is("2009-06-01T14:23:05.003Z"));

		formatter.setIso(ISO.NONE);
		assertThat("uses style pattern", formatter.print(date, Locale.US), is("June 1, 2009"));

		formatter.setStylePattern("");
		assertThat("uses style", formatter.print(date, Locale.US), is("6/1/09"));
	}
