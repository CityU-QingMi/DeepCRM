	@After
	public void tearDown() {
		if ( entityManagerFactory == null ) {
			return;
		}

		deleteTestUser( entityManagerFactory );
		dropProcedures( entityManagerFactory );

		entityManagerFactory.close();
	}
