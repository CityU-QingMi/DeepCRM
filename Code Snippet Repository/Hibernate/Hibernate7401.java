	@Test
	@TestForIssue( jiraKey = "" )
	public void testQueryEntityJoinFetchNullManyToOne() {
		int id = doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = new AnEntity();
					session.persist( anEntity );
					return anEntity.id;
				}
		);

		doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = session.createQuery(
							"from AnEntity e join fetch e.otherEntity where e.id = " + id,
							AnEntity.class
					).uniqueResult();
					assertNull( anEntity );
				}
		);
	}
