	@Test
	@TestForIssue( jiraKey = "" )
	public void testQueryEntityAndNullManyToOne() {
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
					final Object[] result = session.createQuery(
							"select e, e.otherEntity from AnEntity e left join e.otherEntity where e.id = " + id,
							Object[].class
					).uniqueResult();
					assertEquals( 2, result.length );
					assertTrue( AnEntity.class.isInstance( result[0] ) );
					assertNull( result[1] );
				}
		);
	}
