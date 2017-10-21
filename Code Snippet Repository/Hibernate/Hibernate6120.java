	@Test
	@TestForIssue(jiraKey = "")
	public void testGetSingleResultWithNoResultException() {
		final EntityManager entityManager  = getOrCreateEntityManager();
		try {
			entityManager.createQuery( "FROM Item WHERE name = 'bozo'" ).getSingleResult();
			fail( "Expected NoResultException" );
		}
		catch ( Exception e ) {
			assertTyping( NoResultException.class, e );
		}
		finally {
			entityManager.close();
		}
	}
