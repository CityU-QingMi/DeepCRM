	@Test
	public void metadataAssertions() {
		EntityPersister p = sessionFactory().getEntityPersister( Dog.class.getName() );
		assertNotNull( p );
		final JoinedSubclassEntityPersister dogPersister = assertTyping( JoinedSubclassEntityPersister.class, p );
		assertEquals( Ejb3DiscriminatorColumn.DEFAULT_DISCRIMINATOR_TYPE, dogPersister.getDiscriminatorType().getName() );
		assertEquals( Ejb3DiscriminatorColumn.DEFAULT_DISCRIMINATOR_COLUMN_NAME, dogPersister.getDiscriminatorColumnName() );
		assertEquals( "Dog", dogPersister.getDiscriminatorValue() );

		p = sessionFactory().getEntityPersister( Cat.class.getName() );
		assertNotNull( p );
		final JoinedSubclassEntityPersister catPersister = assertTyping( JoinedSubclassEntityPersister.class, p );
		assertEquals( Ejb3DiscriminatorColumn.DEFAULT_DISCRIMINATOR_TYPE, catPersister.getDiscriminatorType().getName() );
		assertEquals( Ejb3DiscriminatorColumn.DEFAULT_DISCRIMINATOR_COLUMN_NAME, catPersister.getDiscriminatorColumnName() );
		assertEquals( "Cat", catPersister.getDiscriminatorValue() );
	}
