	@Test
	public void testLocalDate() {
		final Session s = openSession();
		try {
			final LocalDateEvent localDateEvent = s.get( LocalDateEvent.class, 1L );
			assertThat( localDateEvent.getStartDate(), is( expectedLocalDate ) );
		}
		finally {
			s.close();
		}
	}
