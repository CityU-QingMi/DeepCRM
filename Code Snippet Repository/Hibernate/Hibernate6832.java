    @Test
	public void testWithJpaCompliantNamingStrategy() throws Exception {
		Metadata metadata = new MetadataSources( serviceRegistry )
				.addAnnotatedClass( A.class )
				.addAnnotatedClass( AddressEntry.class )
				.getMetadataBuilder()
				.applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
				.build();

		Collection collectionBinding = metadata.getCollectionBinding( A.class.getName() + ".address" );
		assertEquals(
				"Expecting A#address collection table name (implicit) to be [A_address] per JPA spec (section 11.1.8)",
				"A_ADDRESS",
				collectionBinding.getCollectionTable().getQuotedName().toUpperCase(Locale.ROOT)
		);
	}
