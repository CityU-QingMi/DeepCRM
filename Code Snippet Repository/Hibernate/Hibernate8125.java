	@Test
	@TestForIssue(jiraKey = "")
	@FailureExpected( jiraKey = "" )
	public void testOnClauseUsesNonDrivingTableAlias() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			try {
				entityManager.createQuery("SELECT b1 FROM Book b1 JOIN Book b2 ON b1.author = author2 LEFT JOIN b2.author author2");
				fail("Referring to a join alias in the on clause that is joined later should be invalid!");
			} catch (IllegalArgumentException ex) {
				// TODO: Assert it fails due to the alias not being defined
			}
		} );
	}
