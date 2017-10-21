	@Test
	@TestForIssue( jiraKey = "" )
	@FailureExpected( jiraKey = "" )
	public void testPrimitive() {
		doInHibernate(
				this::sessionFactory,
				session -> {
					AnEntity anEntity = new AnEntity();
					session.persist( anEntity );
					session.flush();
					session.clear();
					anEntity = session.get( AnEntity.class, anEntity.id );
					checkEmptyCompositeTypeEquivalentToNull(
							anEntity.embeddableWithPrimitive,
							"embeddableWithPrimitive",
							sessionFactory()
					);
				}
		);
	}
