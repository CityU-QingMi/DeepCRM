	@Test
	@TestForIssue( jiraKey = "")
	public void testSubstringWithStringUnits() {

		mostRecentStatementInspector.clear();

		doInHibernate(
				this::sessionFactory, session -> {
					String value = session.createQuery(
							"select substring( e.description, 21, 11, octets ) from AnEntity e",
							String.class
					).uniqueResult();
					assertEquals( "description", value );
				}
		);

		assertTrue( mostRecentStatementInspector.mostRecentSql.contains( "substring(" ) );
		assertTrue( mostRecentStatementInspector.mostRecentSql.contains( "octets" ) );
	}
