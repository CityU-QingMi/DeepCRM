	@Test
	@TestForIssue( jiraKey = "" )
	public void testChangedNaturalIdNotCachedAfterTransactionFailure() {
		Session session = openSession();
		session.beginTransaction();
		Another it = new Another( "it");
		session.save( it );
		session.getTransaction().commit();
		session.close();
		
		session = openSession();
		session.beginTransaction();
		it = (Another) session.bySimpleNaturalId(Another.class).load("it");
		assertNotNull(it);
		
		it.setName("modified");
		session.flush();
		session.getTransaction().rollback(); 
		session.close();
		
		session.getSessionFactory().getStatistics().clear();
		
		session = openSession();
		session.beginTransaction();
		it = (Another) session.bySimpleNaturalId(Another.class).load("modified");
		assertNull(it);
		it = (Another) session.bySimpleNaturalId(Another.class).load("it");
		session.delete(it);
		session.getTransaction().commit(); 
		session.close();
		
		assertEquals(0, session.getSessionFactory().getStatistics().getNaturalIdCacheHitCount());
	}
