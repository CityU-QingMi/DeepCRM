	@Test
	public void firstZonedDateTime() {
		ZonedDateTime date = ZonedDateTime.of(2017, 6, 22, 22, 22, 0, 0, ZoneId.of("GMT"));
		headers.setZonedDateTime(HttpHeaders.DATE, date);
		assertThat(headers.getFirst(HttpHeaders.DATE), is("Thu, 22 Jun 2017 22:22:00 GMT"));
		assertTrue(headers.getFirstZonedDateTime(HttpHeaders.DATE).isEqual(date));

		headers.clear();
		ZonedDateTime otherDate = ZonedDateTime.of(2010, 12, 18, 10, 20, 0, 0, ZoneId.of("GMT"));
		headers.add(HttpHeaders.DATE, RFC_1123_DATE_TIME.format(date));
		headers.add(HttpHeaders.DATE, RFC_1123_DATE_TIME.format(otherDate));
		assertTrue(headers.getFirstZonedDateTime(HttpHeaders.DATE).isEqual(date));

		// obsolete RFC 850 format
		headers.clear();
		headers.set(HttpHeaders.DATE, "Thursday, 22-Jun-17 22:22:00 GMT");
		assertTrue(headers.getFirstZonedDateTime(HttpHeaders.DATE).isEqual(date));

		// ANSI C's asctime() format
		headers.clear();
		headers.set(HttpHeaders.DATE, "Thu Jun 22 22:22:00 2017");
		assertTrue(headers.getFirstZonedDateTime(HttpHeaders.DATE).isEqual(date));
	}
