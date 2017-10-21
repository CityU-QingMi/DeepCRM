	@Test
	@TestForIssue(jiraKey = "")
	public void testTableGeneratorQuoting() {
		final Metadata metadata = new MetadataSources( serviceRegistry ).addAnnotatedClass( TestEntity.class ).buildMetadata();

		final ConnectionProvider connectionProvider = serviceRegistry.getService( ConnectionProvider.class );
		final GenerationTarget target = new GenerationTargetToDatabase(
				new DdlTransactionIsolatorTestingImpl(
						serviceRegistry,
						new JdbcEnvironmentInitiator.ConnectionProviderJdbcConnectionAccess( connectionProvider )
				)
		);

		new SchemaCreatorImpl( serviceRegistry ).doCreation( metadata, false, target );

		try {
			new SchemaValidator().validate( metadata );
		}
		catch (HibernateException e) {
			fail( "The identifier generator table should have validated.  " + e.getMessage() );
		}
		finally {
			new SchemaDropperImpl( serviceRegistry ).doDrop( metadata, false, target );
		}
	}
