	@Test
	public void metadataAssertions() {
		EntityPersister p = sessionFactory().getEntityPersister( Dog.class.getName() );
		assertNotNull( p );
		final JoinedSubclassEntityPersister dogPersister = assertTyping( JoinedSubclassEntityPersister.class, p );
		assertEquals( "string", dogPersister.getDiscriminatorType().getName() );
		assertEquals( "type", dogPersister.getDiscriminatorColumnName() );
		assertEquals( "dog", dogPersister.getDiscriminatorValue() );

		p = sessionFactory().getEntityPersister( Cat.class.getName() );
		assertNotNull( p );
		final JoinedSubclassEntityPersister catPersister = assertTyping( JoinedSubclassEntityPersister.class, p );
		assertEquals( "string", catPersister.getDiscriminatorType().getName() );
		assertEquals( "type", catPersister.getDiscriminatorColumnName() );
		assertEquals( "cat", catPersister.getDiscriminatorValue() );
	}
