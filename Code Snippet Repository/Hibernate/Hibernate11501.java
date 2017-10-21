	private void testId(CacheKeysFactory cacheKeysFactory, String entityName, Object id) throws Exception {
		final SessionFactoryImplementor sessionFactory = getSessionFactory( cacheKeysFactory.getClass().getName() );
		final EntityPersister persister = sessionFactory.getEntityPersister( entityName );
		final Object key = cacheKeysFactory.createEntityKey(
				id,
				persister,
				sessionFactory,
				null
		);

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject( key );

		final ObjectInputStream ois = new ObjectInputStream( new ByteArrayInputStream( baos.toByteArray() ) );
		final Object keyClone = ois.readObject();

		try {
			assertEquals( key, keyClone );
			assertEquals( keyClone, key );

			assertEquals( key.hashCode(), keyClone.hashCode() );

			final Object idClone = cacheKeysFactory.getEntityId( keyClone );

			assertEquals( id.hashCode(), idClone.hashCode() );
			assertEquals( id, idClone );
			assertEquals( idClone, id );
			assertTrue( persister.getIdentifierType().isEqual( id, idClone, sessionFactory ) );
			assertTrue( persister.getIdentifierType().isEqual( idClone, id, sessionFactory ) );
			sessionFactory.close();
		}
		finally {
			sessionFactory.close();
		}
	}
