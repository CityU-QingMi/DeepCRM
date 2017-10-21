	public void testLimitWithExpreesionAndFetchJoin() {
		Session session = openSession();
		session.beginTransaction();

		String hql = "SELECT b, 1 FROM DataMetaPoint b inner join fetch b.dataPoint dp";
		session.createQuery(hql)
				.setMaxResults(3)
				// This should not fail
				.list();

		HQLQueryPlan queryPlan = new HQLQueryPlan(hql, false, Collections.EMPTY_MAP, sessionFactory());
		String sqlQuery = queryPlan.getTranslators()[0]
				.collectSqlStrings().get(0);

		session.getTransaction().commit();
		session.close();

		Matcher matcher = Pattern.compile(
				"(?is)\\b(?<column>\\w+\\.\\w+)\\s+as\\s+(?<alias>\\w+)\\b.*\\k<column>\\s+as\\s+\\k<alias>")
				.matcher(sqlQuery);
		if (matcher.find()) {
			fail(format("Column %s mapped to alias %s twice in generated SQL: %s", matcher.group("column"),
					matcher.group("alias"), sqlQuery));
		}
	}
