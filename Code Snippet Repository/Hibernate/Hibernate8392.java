	@Test
	public void metadataAssertions() {
		EntityPersister p = sessionFactory().getEntityPersister( Dog.class.getName() );
		assertNotNull( p );
		final JoinedSubclassEntityPersister dogPersister = assertTyping( JoinedSubclassEntityPersister.class, p );
		assertEquals( "integer", dogPersister.getDiscriminatorType().getName() );
		assertEquals( "clazz_", dogPersister.getDiscriminatorColumnName() );
		assertTrue( Integer.class.isInstance( dogPersister.getDiscriminatorValue() ) );

		p = sessionFactory().getEntityPersister( Cat.class.getName() );
		assertNotNull( p );
		final JoinedSubclassEntityPersister catPersister = assertTyping( JoinedSubclassEntityPersister.class, p );
		assertEquals( "integer", catPersister.getDiscriminatorType().getName() );
		assertEquals( "clazz_", catPersister.getDiscriminatorColumnName() );
		assertTrue( Integer.class.isInstance( catPersister.getDiscriminatorValue() ) );
	}
