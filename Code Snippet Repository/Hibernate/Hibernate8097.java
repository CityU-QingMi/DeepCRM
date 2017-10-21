	@Test
	@TestForIssue( jiraKey = "" )
	public void testExplicitJoinsInSubquery() {
		assertTranslation(
		        "from org.hibernate.test.hql.Animal as animal " +
		        "where animal.id in (" +
		        "        select a.id " +
		        "        from org.hibernate.test.hql.Animal as a " +
		        "               left join a.mother as mo" +
		        ")"
		);
	}
