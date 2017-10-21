	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testWithScroll() {
		// Creates data necessary for test
		Long facilityId = populate();
		// Controller iterates the data
		for ( OrderLine line : getOrderLinesScrolled( facilityId ) ) {
			// This should ~NOT~ fail with a LazilyLoadException
			assertNotNull( line.getProduct().getFacility().getSite().getName() );
		}
	}
