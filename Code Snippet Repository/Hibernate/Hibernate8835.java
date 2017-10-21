	@Test
	public void testDefaultNamingStrategy() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			final MetadataSources ms = new MetadataSources( ssr );
			ms.addAnnotatedClass( Container.class ).addAnnotatedClass( Item.class );

			final Metadata metadata = ms.getMetadataBuilder()
					.applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
					.build();

			final PersistentClass pc = metadata.getEntityBinding( Container.class.getName() );
			Property p = pc.getProperty( "items" );
			Bag value = assertTyping( Bag.class, p.getValue() );
			SimpleValue elementValue = assertTyping( SimpleValue.class, value.getElement() );
			assertEquals( 1, elementValue.getColumnSpan() );
			Column column = assertTyping( Column.class, elementValue.getColumnIterator().next() );
			assertEquals( column.getName(), "name" );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
