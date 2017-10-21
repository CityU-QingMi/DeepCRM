	private void verifyFkNameUsed(String mappingResource, String expectedName) {
		final Metadata metadata = new MetadataSources( ssr )
				.addResource( mappingResource )
				.buildMetadata();

		final JournalingSchemaToolingTarget target = new JournalingSchemaToolingTarget();
		new SchemaCreatorImpl( ssr ).doCreation(
				metadata,
				ssr,
				ssr.getService( ConfigurationService.class ).getSettings(),
				false,
				target
		);

		assertTrue(
				"Expected foreign-key name [" + expectedName + "] not seen in schema creation output",
				target.containedText( expectedName )
		);
	}
