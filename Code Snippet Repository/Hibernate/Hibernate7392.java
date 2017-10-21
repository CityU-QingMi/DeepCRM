	@Test
	public void testQueryEntityJoinFetchEmptyCollection() {
		AnEntity.PK id = doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = new AnEntity( new AnEntity.PK( "first", "last" ) );
					session.persist( anEntity );
					return anEntity.id;
				}
		);

		doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = session.createQuery(
							"from AnEntity e join fetch e.names where e.id = :id ",
							AnEntity.class
					).setParameter( "id", id ).uniqueResult();
					assertNull( anEntity );
				}
		);
	}
