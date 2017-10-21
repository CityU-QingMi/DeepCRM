	@Test
	@TestForIssue(jiraKey = "")
	public void testGetLimitStringWithMaxOnly() {
		final String query = "select product2x0_.id as id0_, product2x0_.description as descript2_0_ " +
				"from Product2 product2x0_ order by product2x0_.id";

		assertEquals(
				"select TOP(?) product2x0_.id as id0_, product2x0_.description as descript2_0_ " +
						"from Product2 product2x0_ order by product2x0_.id",
				dialect.getLimitHandler().processSql( query, toRowSelection( 0, 1 ) )
		);

		final String distinctQuery = "select distinct product2x0_.id as id0_, product2x0_.description as descript2_0_ " +
				"from Product2 product2x0_ order by product2x0_.id";

		assertEquals(
				"select distinct TOP(?) product2x0_.id as id0_, product2x0_.description as descript2_0_ " +
						"from Product2 product2x0_ order by product2x0_.id",
				dialect.getLimitHandler().processSql( distinctQuery, toRowSelection( 0, 5 ) )
		);
	}
