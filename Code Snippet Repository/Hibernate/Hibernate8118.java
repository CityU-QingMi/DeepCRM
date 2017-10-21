	@Test
	public void testCollectionJoinsInSubselect() {
		// caused by some goofiness in FromElementFactory that tries to
		// handle correlated subqueries (but fails miserably) even though this
		// is not a correlated subquery.  HHH-1248
		assertTranslation(
				"select a.id, a.description" +
				" from Animal a" +
				"       left join a.offspring" +
				" where a in (" +
				"       select a1 from Animal a1" +
				"           left join a1.offspring o" +
				"       where a1.id=1" +
		        ")"
		);
		assertTranslation(
				"select h.id, h.description" +
		        " from Human h" +
				"      left join h.friends" +
				" where h in (" +
				"      select h1" +
				"      from Human h1" +
				"          left join h1.friends f" +
				"      where h1.id=1" +
				")"
		);
	}
