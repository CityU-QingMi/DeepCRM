	@Test
	@TestForIssue( jiraKey = "" )
	public void testMultipleUsesOfDefaultSequenceName() {
		final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
				.addAnnotatedClass( Entity1.class )
				.addAnnotatedClass( Entity2.class )
				.buildMetadata();
		metadata.validate();

		assertEquals( 0, metadata.getDatabase().getAuxiliaryDatabaseObjects().size() );

		int count = 0;
		for ( Namespace namespace : metadata.getDatabase().getNamespaces() ) {
			for ( Sequence sequence : namespace.getSequences() ) {
				count++;
			}
		}

		assertEquals( 1, count );
	}
