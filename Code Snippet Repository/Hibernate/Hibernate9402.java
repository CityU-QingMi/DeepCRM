	@Test
	public void testValidation() {
		MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
				.addAnnotatedClass( Translation.class )
				.buildMetadata();
		metadata.validate();


		// create the schema
		createSchema( metadata );

		try {
			doValidation( metadata );
		}
		finally {
			dropSchema( metadata );
		}
	}
