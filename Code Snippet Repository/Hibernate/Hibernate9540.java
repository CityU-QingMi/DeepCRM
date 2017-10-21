	@Before
	public void setUp() throws Exception {
		connectionProvider =
				new DriverManagerConnectionProviderImpl();
		connectionProvider.configure( properties() );

		connection = connectionProvider.getConnection();

		ssr = new StandardServiceRegistryBuilder()
			.applySetting( AvailableSettings.HBM2DDL_CONNECTION, connection )
			.build();

		tool = (HibernateSchemaManagementTool) ssr.getService( SchemaManagementTool.class );

		configurationValues = ssr.getService( ConfigurationService.class ).getSettings();

		executionOptions = new ExecutionOptions() {
			@Override
			public boolean shouldManageNamespaces() {
				return true;
			}

			@Override
			public Map getConfigurationValues() {
				return configurationValues;
			}

			@Override
			public ExceptionHandler getExceptionHandler() {
				return ExceptionHandlerLoggedImpl.INSTANCE;
			}
		};
	}
