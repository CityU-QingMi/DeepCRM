	@Test
    @SkipForDialect( H2Dialect.class )
	public void testFailOnNoAddEntityOrScalar() {
		// Note: this passes, but for the wrong reason.
		//      there is actually an exception thrown, but it is the database
		//      throwing a sql exception because the SQL gets passed
		//      "un-processed"...
		//
		// Oddly, H2 accepts this query.
		Session s = openSession();
		s.beginTransaction();
		try {
			String sql = "select {org.*} " +
			             "from organization org";
			s.createSQLQuery( sql ).list();
			fail( "Should throw an exception since no addEntity nor addScalar has been performed." );
		}
		catch( PersistenceException pe) {
			// expected behavior
		}
		finally {
			s.getTransaction().rollback();
			s.close();
		}
	}
