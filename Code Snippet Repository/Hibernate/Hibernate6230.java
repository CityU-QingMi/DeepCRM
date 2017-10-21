	@Test
	@TestForIssue(jiraKey = "")
	public void testOnlyOneInstanceOfTheServiceShouldBeCreated() throws InterruptedException, ExecutionException {

		Future<SlowInitializationService>[] serviceIdentities = execute();

		SlowInitializationService previousResult = null;
		for ( Future<SlowInitializationService> future : serviceIdentities ) {
			final SlowInitializationService result = future.get();
			if ( previousResult == null ) {
				previousResult = result;
			}
			else {
				assertTrue( "There are more than one instance of the service", result == previousResult );
			}

		}

		standardServiceRegistryBuilder.destroy( registry );

	}
