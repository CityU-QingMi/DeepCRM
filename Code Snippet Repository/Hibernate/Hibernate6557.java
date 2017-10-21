	@Test
	@TestForIssue( jiraKey = "" )
	public void testEnumeratedTypeResolutions() {
		final MetadataImplementor mappings = (MetadataImplementor) new MetadataSources( ssr )
				.addAnnotatedClass( EntityWithEnumeratedAttributes.class )
				.buildMetadata();
		mappings.validate();

		final PersistentClass entityBinding = mappings.getEntityBinding( EntityWithEnumeratedAttributes.class.getName() );

		validateEnumMapping( entityBinding.getProperty( "notAnnotated" ), EnumType.ORDINAL );
		validateEnumMapping( entityBinding.getProperty( "noEnumType" ), EnumType.ORDINAL );
		validateEnumMapping( entityBinding.getProperty( "ordinalEnumType" ), EnumType.ORDINAL );
		validateEnumMapping( entityBinding.getProperty( "stringEnumType" ), EnumType.STRING );
	}
