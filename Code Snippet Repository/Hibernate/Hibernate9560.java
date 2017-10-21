	@Test
	public void basicTests() {
		final PersistentClass entityBinding = metadata().getEntityBinding( TheEntity.class.getName() );
		final Iterator propertyBindingIterator = entityBinding.getPropertyClosureIterator();
		while ( propertyBindingIterator.hasNext() ) {
			final Property propertyBinding = (Property) propertyBindingIterator.next();
			assertFalse(
					"Found property bound as Serializable : " + propertyBinding.getName(),
					propertyBinding.getType() instanceof SerializableType
			);
		}

		TheEntity theEntity = new TheEntity( 1 );

		Session s = openSession();
		s.beginTransaction();
		s.save( theEntity );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		theEntity = (TheEntity) s.get( TheEntity.class, 1 );
		dump( entityBinding, theEntity );
		assertNotNull( theEntity );
		s.delete( theEntity );
		s.getTransaction().commit();
		s.close();
	}
