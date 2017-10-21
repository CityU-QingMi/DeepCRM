	private void verifyIndexCreated(String mappingResource, String expectedIndexName) {
		final Metadata metadata = new MetadataSources( ssr )
				.addResource( mappingResource )
				.buildMetadata();

		final JournalingSchemaToolingTarget target = new JournalingSchemaToolingTarget();
		new SchemaCreatorImpl( ssr ).doCreation( metadata, false, target );

		assertTrue(
				"Expected index [" + expectedIndexName + "] not seen in schema creation output",
				target.containedText( expectedIndexName )
		);
	}
