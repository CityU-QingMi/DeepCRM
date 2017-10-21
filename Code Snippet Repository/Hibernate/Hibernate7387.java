	@Test
	public void testGetEntityWithEmptyCollection() {
		AnEntity.PK id = doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = new AnEntity( new AnEntity.PK( "first", "last" ));
					session.persist( anEntity );
					return anEntity.id;
				}
		);

		doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = session.find( AnEntity.class, id );
					assertTrue( Hibernate.isInitialized( anEntity.names ) );
					assertTrue( anEntity.names.isEmpty() );
				}
		);
	}
