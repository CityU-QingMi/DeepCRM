	@Override
	protected void cleanupTest() throws Exception {
		try {
			Connection conn = cp.getConnection();

			try {
				final GenerationTargetToDatabase target = new GenerationTargetToDatabase(
						new DdlTransactionIsolatorTestingImpl(
								serviceRegistry(),
								conn
						),
						true
				);
				new SchemaDropperImpl( serviceRegistry() ).doDrop( metadata(), false, target );
			}
			finally {
				cp.closeConnection( conn );
			}
		}
		catch( Throwable ignore ) {
		}

		super.cleanupTest();
	}
