	@Test
	public void testHistoryOfId1() {
		Country country1 = getEntityManager().find(
				Country.class,
				country.getCode()
		);
		assertEquals( country1, country );

		Country history = getAuditReader().find( Country.class, country1.getCode(), 1 );
		assertEquals( country, history );
	}
