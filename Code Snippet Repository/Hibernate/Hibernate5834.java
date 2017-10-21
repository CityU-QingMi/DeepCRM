	@Test
	@TestForIssue( jiraKey = "" )
	public void testInterceptor() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Instrument instrument = new Instrument();
		instrument.setName( "Guitar" );
		try {
			em.persist( instrument );
			fail( "Commit should have failed." );
		}
		catch ( RuntimeException e ) {
			assertTrue( em.getTransaction().getRollbackOnly() );
		}
		em.close();
	}
