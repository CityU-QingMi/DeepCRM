	@Test
	public void testUniqueKeyNotGeneratedViaAnnotations() throws Exception {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();
		try {
			Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( Parent.class )
					.addAnnotatedClass( Child.class )
					.buildMetadata();

			Table childTable = metadata.getDatabase().getDefaultNamespace().locateTable( Identifier.toIdentifier( "CHILD" ) );
			assertFalse( "UniqueKey was generated when it should not", childTable.getUniqueKeyIterator().hasNext() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
