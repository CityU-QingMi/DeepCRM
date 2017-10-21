	@Test
	public void testUpdateNonNullValueToNullToNonNull() {
		int entityId = doInHibernate(
				this::sessionFactory, session -> {
					AnEntity e = new AnEntity();
					e.aCollection.add( "def" );
					e.aCollection.add( "ghi" );
					session.persist( e );
					return e.id;
				}
		);

		doInHibernate(
				this::sessionFactory, session -> {
					AnEntity e = session.get( AnEntity.class, entityId );
					assertEquals( 2, e.aCollection.size() );
					assertEquals( 2, getCollectionElementRows( e.id ).size() );
					e.aCollection.set( 0, null );
				}
		);

		doInHibernate(
				this::sessionFactory, session -> {
					AnEntity e = session.get( AnEntity.class, entityId );
					assertEquals( 2, e.aCollection.size() );
					assertEquals( 1, getCollectionElementRows( e.id ).size() );
					e.aCollection.set( 0, "not null" );
				}
		);

		doInHibernate(
				this::sessionFactory, session -> {
					AnEntity e = session.get( AnEntity.class, entityId );
					assertEquals( 2, e.aCollection.size() );
					assertEquals( 2, getCollectionElementRows( e.id ).size() );
					assertEquals( "not null", e.aCollection.get( 0 ) );
					assertEquals( "ghi", e.aCollection.get( 1 ) );
					session.delete( e );
				}
		);
	}
