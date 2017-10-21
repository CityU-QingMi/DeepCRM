	@Test
	@TestForIssue( jiraKey = "" )
	public void testColumnAnnotationWithExplicitReferenceToPrimaryTable() {
		final PersistenceUnitDescriptorAdapter pu = new PersistenceUnitDescriptorAdapter() {
			@Override
			public List<String> getManagedClassNames() {
				return Arrays.asList( AnEntity.class.getName() );
			}
		};


		final Map settings = new HashMap();
		settings.put( AvailableSettings.HBM2DDL_AUTO, "create-drop" );

		EntityManagerFactory emf = Bootstrap.getEntityManagerFactoryBuilder( pu, settings ).build();
		emf.close();
	}
