	@Test
	@TestForIssue( jiraKey = "" )
	public void testQueryEntityWithNullManyToOne() {
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
							"from AnEntity where id = " + id,
							AnEntity.class
					).uniqueResult();
					assertNull( anEntity.otherEntity );
				}
		);
	}
