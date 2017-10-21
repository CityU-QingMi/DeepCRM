	@Test
	public void firstDate() {
		headers.setDate(HttpHeaders.DATE, 1229595600000L);
		assertThat(headers.getFirstDate(HttpHeaders.DATE), is(1229595600000L));

		headers.clear();

		headers.add(HttpHeaders.DATE, "Thu, 18 Dec 2008 10:20:00 GMT");
		headers.add(HttpHeaders.DATE, "Sat, 18 Dec 2010 10:20:00 GMT");
		assertThat(headers.getFirstDate(HttpHeaders.DATE), is(1229595600000L));
	}
