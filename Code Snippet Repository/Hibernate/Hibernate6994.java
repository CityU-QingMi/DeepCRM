	@Test
	public void testEnumTypeInterpretation() {
		final PreparedStatementSpyConnectionProvider connectionProvider = new PreparedStatementSpyConnectionProvider();

		final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.enableAutoClose()
				.applySetting( AvailableSettings.HBM2DDL_AUTO, "update" )
				.applySetting(
						AvailableSettings.CONNECTION_PROVIDER,
						connectionProvider
				)
				.build();

		SessionFactory sessionFactory = null;
		try {
			final Metadata metadata = new MetadataSources( serviceRegistry )
					.addAnnotatedClass( Customer.class )
					.buildMetadata();
			sessionFactory = metadata.buildSessionFactory();
			List<String> alterStatements = connectionProvider.getExecuteStatements().stream()
					.filter(
							sql -> sql.toLowerCase().contains( "alter " )
					).map( String::trim ).collect( Collectors.toList() );
			assertTrue( alterStatements.get( 0 ).matches( "alter table CUSTOMER\\s+drop index .*?" ) );
			assertTrue( alterStatements.get( 1 )
								.matches( "alter table CUSTOMER\\s+add constraint .*? unique \\(CUSTOMER_ID\\)" ) );
		}
		finally {
			if ( sessionFactory != null ) {
				sessionFactory.close();
			}
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
