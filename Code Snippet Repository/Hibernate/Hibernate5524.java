	@Test
	@TestForIssue( jiraKey = "" )
	public void testGet() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Item item = em.getReference( Item.class, "nonexistentone" );
		try {
			item.getDescr();
			em.getTransaction().commit();
			fail( "Object with wrong id should have failed" );
		}
		catch ( EntityNotFoundException e ) {
			//success
			if ( em.getTransaction() != null ) {
				em.getTransaction().rollback();
			}
		}
		finally {
			em.close();
		}
	}
