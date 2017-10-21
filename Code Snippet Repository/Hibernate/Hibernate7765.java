	@Test
	@TestForIssue( jiraKey = "" )
	public void testEvictingNonEntity() {
		Session session = openSession();
		session.beginTransaction();
		try {
			session.evict( new EvictionTest() );
			fail( "Expecting evict(non-entity) to throw IAE" );
		}
		catch (IllegalArgumentException expected) {
		}
		session.getTransaction().commit();
		session.close();
	}
