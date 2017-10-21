	@Override
	protected void prepareTest() throws Exception {
		super.prepareTest();

		try {
			Connection conn = cp.getConnection();

			try {
				final GenerationTargetToDatabase target = new GenerationTargetToDatabase(
						new DdlTransactionIsolatorTestingImpl( serviceRegistry(), conn ),
						true
				);
				new SchemaCreatorImpl( serviceRegistry() ).doCreation(
						metadata(),
						false,
						target
				);
			}
			finally {
				cp.closeConnection( conn );
			}
		}
		catch( Throwable ignore ) {
		}

	}
