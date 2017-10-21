	@Test
	@TestForIssue( jiraKey = "" )
	public void testNonEntity() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.getReference( String.class, 1 );
			fail( "Expecting a failure" );
		}
		catch (IllegalArgumentException ignore) {
			// expected
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
