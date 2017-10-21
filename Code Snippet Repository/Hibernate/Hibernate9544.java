	@Test
	public void testMissingEntityContainsUnqualifiedEntityName() throws Exception {
		final MetadataSources metadataSources = new MetadataSources( ssr );
		metadataSources.addAnnotatedClass( UnqualifiedMissingEntity.class );

		final MetadataImplementor metadata = (MetadataImplementor) metadataSources.buildMetadata();
		metadata.validate();

		try {
			getSchemaValidator( metadata );
			Assert.fail( "SchemaManagementException expected" );
		}
		catch (SchemaManagementException e) {
			assertEquals("Schema-validation: missing table [UnqualifiedMissingEntity]", e.getMessage());
		}
	}
