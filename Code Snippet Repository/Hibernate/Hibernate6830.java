    @Test
	public void testWithCustomNamingStrategy() throws Exception {
		Metadata metadata = new MetadataSources( serviceRegistry )
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Person.class)
				.getMetadataBuilder()
				.applyImplicitNamingStrategy( new LongIdentifierNamingStrategy() )
				.build();

		org.hibernate.mapping.ForeignKey foreignKey =
				(org.hibernate.mapping.ForeignKey) metadata.getEntityBinding( Address.class.getName()).getTable().getForeignKeyIterator().next();
		assertEquals( "FK_way_longer_than_the_30_char", foreignKey.getName() );

		UniqueKey uniqueKey = metadata.getEntityBinding( Address.class.getName()).getTable().getUniqueKeyIterator().next();
		assertEquals( "UK_way_longer_than_the_30_char", uniqueKey.getName() );

		org.hibernate.mapping.Index index = metadata.getEntityBinding( Address.class.getName()).getTable().getIndexIterator().next();
		assertEquals( "IDX_way_longer_than_the_30_cha", index.getName() );
	}
