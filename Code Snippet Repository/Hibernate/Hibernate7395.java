	@Test
	@TestForIssue( jiraKey = "" )
	public void testParent() {
		doInHibernate(
				this::sessionFactory,
				session -> {
					AnEntity anEntity = new AnEntity();
		 			session.persist( anEntity );
					session.flush();
					session.clear();
					anEntity = session.get( AnEntity.class, anEntity.id );
					checkEmptyCompositeTypeEquivalentToNull(
							anEntity.embeddableWithParent,
							"embeddableWithParent",
							sessionFactory()
					);
				}
		);

	}
