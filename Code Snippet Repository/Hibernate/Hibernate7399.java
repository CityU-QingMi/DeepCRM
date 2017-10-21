	@Test
	@TestForIssue( jiraKey = "" )
	public void testGetEntityWithNullManyToOne() {
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
					final AnEntity anEntity = session.find( AnEntity.class, id );
					assertNotNull( anEntity );
					assertNull( anEntity.otherEntity );
				}
		);
	}
