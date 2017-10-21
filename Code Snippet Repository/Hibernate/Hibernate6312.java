	@Test
	public void testCollectionAsBasic() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();
		try {
			Metadata metadata = new MetadataSources(ssr).addAnnotatedClass( Post.class )
					.getMetadataBuilder().applyBasicType( new DelimitedStringsType() )
					.build();
			PersistentClass postBinding = metadata.getEntityBinding( Post.class.getName() );
			Property tagsAttribute = postBinding.getProperty( "tags" );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
