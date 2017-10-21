	@Test
	@TestForIssue(jiraKey = "")
	public void testFailingNativeQuery() {
		final EntityManager entityManager = getOrCreateEntityManager();
		try {
			// Tests that Oracle does not run out of cursors.
			for ( int i = 0; i < 1000; i++ ) {
				try {
					entityManager.createNativeQuery( "Select 1 from NotExistedTable" ).getResultList();
					fail( "expected PersistenceException" );
				}
				catch (PersistenceException e) {
					// expected
				}
			}
		}finally {
			entityManager.close();
		}

	}
