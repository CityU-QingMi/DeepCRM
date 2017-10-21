	@Test
	@TestForIssue( jiraKey = "" )
	public void testInsertUpdateEntity_NaturalIdCachedAfterTransactionSuccess() {
		
		Session session = openSession();
		session.getSessionFactory().getStatistics().clear();
		session.beginTransaction();
		Another it = new Another( "it");
		session.save( it );    // schedules an InsertAction
		it.setSurname("1234"); // schedules an UpdateAction, without bug-fix
		// this will re-cache natural-id with identical key and at same time invalidate it
		session.flush();
		session.getTransaction().commit();
		session.close();
		
		session = openSession();
		session.beginTransaction();
		it = (Another) session.bySimpleNaturalId(Another.class).load("it");
		assertNotNull(it);
		session.delete(it);
		session.getTransaction().commit();
		assertEquals("In a strict access strategy we would excpect a hit here", 1, session.getSessionFactory().getStatistics().getNaturalIdCacheHitCount());
	}
