	@Test
	public void testUpdateNonNullValueToNullWithExtraValue() {
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
					if ( "def".equals( e.aCollection.get( 0 ) ) ) {
						e.aCollection.set( 0, null );
					}
					else {
						e.aCollection.set( 1, null );
					}
				}
		);

		doInHibernate(
				this::sessionFactory, session -> {
					AnEntity e = session.get( AnEntity.class, entityId );
					assertEquals( 1, e.aCollection.size() );
					assertEquals( 1, getCollectionElementRows( e.id ).size() );
					assertEquals( "ghi", e.aCollection.get( 0 ) );
					session.delete( e );
				}
		);
	}
