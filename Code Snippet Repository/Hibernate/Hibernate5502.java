	@After
	@SuppressWarnings( {""})
	public void releaseResources() {
		try {
			releaseUnclosedEntityManagers();
		}
		finally {
			if ( entityManagerFactory != null && entityManagerFactory.isOpen()) {
				entityManagerFactory.close();
			}
		}
		// Note we don't destroy the service registry as we are not the ones creating it
	}
