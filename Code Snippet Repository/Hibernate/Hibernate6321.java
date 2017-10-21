	@Test
	@RequiresDialectFeature(DialectChecks.SupportsColumnCheck.class)
	public void testMinAndMaxChecksGetApplied() {
		MinMax minMax = new MinMax( 1 );
		assertDatabaseConstraintViolationThrown( minMax );

		minMax = new MinMax( 11 );
		assertDatabaseConstraintViolationThrown( minMax );

		minMax = new MinMax( 5 );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( minMax );
		s.flush();
		tx.rollback();
		s.close();
	}
