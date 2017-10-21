	@SuppressWarnings( {""})
	@Test
	public void testDistinctSelectWithJoin() {
		feedDatabase();

		Session s = openSession();

		List<Entry> entries = s.createQuery("select distinct e from Entry e join e.tags t where t.surrogate != null order by e.name").setFirstResult(10).setMaxResults(5).list();

		// System.out.println(entries);
		Entry firstEntry = entries.remove(0);
		assertFalse("The list of entries should not contain dublicated Entry objects as we've done a distinct select", entries.contains(firstEntry));

		s.close();
	}
