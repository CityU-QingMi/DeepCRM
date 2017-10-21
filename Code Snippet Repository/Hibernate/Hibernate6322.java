	@Test
	@RequiresDialectFeature(DialectChecks.SupportsColumnCheck.class)
	public void testRangeChecksGetApplied() {
		Range range = new Range( 1 );
		assertDatabaseConstraintViolationThrown( range );

		range = new Range( 11 );
		assertDatabaseConstraintViolationThrown( range );

		range = new Range( 5 );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( range );
		s.flush();
		tx.rollback();
		s.close();
	}
