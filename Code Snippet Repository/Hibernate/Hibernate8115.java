	@Test
	@TestForIssue(jiraKey = "")
	public void testBogusCreateQuery() {
		Session session = openSession();
		try {
			session.beginTransaction();
			session.createQuery( "Bogus" );
			fail( "This should have failed with an IllegalArgumentException" );
		}
		catch ( IllegalArgumentException e ) {
			if ( session.getTransaction().isActive() ) {
				session.getTransaction().rollback();
			}
			assertTyping( QueryException.class, e.getCause() );
		}
		finally {
			session.close();
		}
	}
