	@After
	public void tearDown() {
		if ( entityManagerFactory == null ) {
			return;
		}

		deleteTestData( entityManagerFactory );
		dropProcedures( entityManagerFactory );

		entityManagerFactory.close();
	}
