	@Test
	@TestForIssue( jiraKey = "" )
	public void testNamedQueryWithMarkForRollbackOnlyFailure() {
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				try {
					Mockito.reset( transactionCoordinator );
					doNothing().
					doThrow(IllegalStateException.class).when( transactionCoordinator ).pulse();

					entityManager.createNamedQuery( "NamedQuery" );
				}
				catch (Exception e) {
					assertEquals(IllegalArgumentException.class, e.getClass());
					assertEquals(IllegalStateException.class, e.getCause().getClass());
				}
			});
		}
		catch (Exception ignore) {
		}
	}
