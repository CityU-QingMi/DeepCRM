	@Test
	@TestForIssue( jiraKey = "" )
	public void testQueryEntityLeftJoinFetchNullManyToOne() {
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
							"from AnEntity e left join fetch e.otherEntity where e.id = " + id,
							AnEntity.class
					).uniqueResult();
					assertNull( anEntity.otherEntity );
				}
		);
	}
