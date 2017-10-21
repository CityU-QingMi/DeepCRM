	@Test
	@TestForIssue( jiraKey = "" )
	public void testEvictingNull() {
		Session session = openSession();
		session.beginTransaction();
		try {
			session.evict( null );
			fail( "Expecting evict(null) to throw NPE" );
		}
		catch (NullPointerException expected) {
		}
		session.getTransaction().commit();
		session.close();
	}
