	@Before
	public void setUp() throws Exception {
		serviceRegistry = new StandardServiceRegistryBuilder()
				.enableAutoClose()
				.applySetting( AvailableSettings.HBM2DDL_AUTO, "create-drop" )
				.build();

		generator = new SequenceStyleGenerator();

		// Build the properties used to configure the id generator
		Properties properties = new Properties();
		properties.setProperty( SequenceStyleGenerator.SEQUENCE_PARAM, TEST_SEQUENCE );
		properties.setProperty( SequenceStyleGenerator.OPT_PARAM, "legacy-hilo" );
		properties.setProperty( SequenceStyleGenerator.INCREMENT_PARAM, "0" ); // JPA allocationSize of 1
		properties.put(
				PersistentIdentifierGenerator.IDENTIFIER_NORMALIZER,
				new ObjectNameNormalizer() {
					@Override
					protected MetadataBuildingContext getBuildingContext() {
						return new MetadataBuildingContextTestingImpl( serviceRegistry );
					}
				}
		);
		generator.configure( StandardBasicTypes.LONG, properties, serviceRegistry );

		final Metadata metadata = new MetadataSources( serviceRegistry ).buildMetadata();
		generator.registerExportables( metadata.getDatabase() );

		sessionFactory = (SessionFactoryImplementor) metadata.buildSessionFactory();
		sequenceValueExtractor = new SequenceValueExtractor( sessionFactory.getDialect(), TEST_SEQUENCE );
	}
