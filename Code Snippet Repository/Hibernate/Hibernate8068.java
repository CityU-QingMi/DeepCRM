	@Test
	@TestForIssue( jiraKey = "")
	public void testSubstrWithStringUnits() {

		mostRecentStatementInspector.clear();

		try {
			doInHibernate(
					this::sessionFactory, session -> {
						String value = session.createQuery(
								"select substr( e.description, 21, 11, octets ) from AnEntity e",
								String.class
						).uniqueResult();
						assertEquals( "description", value );
					}
			);
			fail( "Should have failed because substr cannot be used with string units." );
		}
		catch (PersistenceException expected) {
			assertTrue( SQLGrammarException.class.isInstance( expected.getCause() ) );
		}

		assertTrue( mostRecentStatementInspector.mostRecentSql.contains( "substr(" ) );
		assertTrue( mostRecentStatementInspector.mostRecentSql.contains( "octets" ) );
	}
