	@Test
	@TestForIssue( jiraKey = "" )
	@FailureExpected( jiraKey = "" )
	public void testMappedSuperclassAccessNoEntity() {
		// stupid? yes.  tck does it? yes.

		final PersistenceUnitDescriptorAdapter pu = new PersistenceUnitDescriptorAdapter() {
			@Override
			public List<String> getManagedClassNames() {
				// pass in a MappedSuperclass that is not used in any entity hierarchy
				return Arrays.asList( SomeMappedSuperclass.class.getName() );
			}
		};

		final Map settings = new HashMap();
		settings.put( AvailableSettings.HBM2DDL_AUTO, "create-drop" );

		EntityManagerFactory emf = Bootstrap.getEntityManagerFactoryBuilder( pu, settings ).build();
		try {
			ManagedType<SomeMappedSuperclass> type = emf.getMetamodel().managedType( SomeMappedSuperclass.class );
			// the issue was in regards to throwing an exception, but also check for nullness
			assertNotNull( type );
		}
		finally {
			emf.close();
		}
	}
