	@Test
	public void testMissingEntityContainsQualifiedEntityName() throws Exception {
		final MetadataSources metadataSources = new MetadataSources( ssr );
		metadataSources.addAnnotatedClass( MissingEntity.class );

		final MetadataImplementor metadata = (MetadataImplementor) metadataSources.buildMetadata();
		metadata.validate();

		try {
			getSchemaValidator( metadata );
			Assert.fail( "SchemaManagementException expected" );
		}
		catch (SchemaManagementException e) {
			assertEquals("Schema-validation: missing table [SomeCatalog.SomeSchema.MissingEntity]", e.getMessage());
		}
	}
