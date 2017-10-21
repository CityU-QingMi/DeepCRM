	@Test
	@TestForIssue( jiraKey = "")
	public void testSubstrWithoutStringUnits() {

		mostRecentStatementInspector.clear();

		doInHibernate(
				this::sessionFactory, session -> {
					String value = session.createQuery(
							"select substr( e.description, 21, 11 ) from AnEntity e",
							String.class
					).uniqueResult();
					assertEquals( "description", value );
				}
		);
		assertTrue( mostRecentStatementInspector.mostRecentSql.contains( "substr(" ) );
	}
