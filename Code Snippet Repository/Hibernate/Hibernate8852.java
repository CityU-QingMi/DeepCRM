	@Test
	public void test() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.build();

		try {
			Metadata meta = new MetadataSources( ssr )
					.addAnnotatedClass( PostalCarrier.class )
					.addAnnotatedClass( PostalCode.class )
					.buildMetadata();
			( (MetadataImplementor) meta ).validate();
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
