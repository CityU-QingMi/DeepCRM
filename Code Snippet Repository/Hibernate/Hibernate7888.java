	@Test
	public void testOrderByAggregatedArgAliasRef() {
		createData();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		// ordered by name, address:
		//   zoo2  A Zoo       1313 Mockingbird Lane, Anywhere, IL USA
		//   zoo4  Duh Zoo     1312 Mockingbird Lane, Nowhere, IL USA
		//   zoo3  Zoo         1312 Mockingbird Lane, Anywhere, IL USA
		//   zoo1  Zoo         1313 Mockingbird Lane, Anywhere, IL USA
		List list =
				s.createQuery(
						"select z.name as zname, count(*) as cnt from Zoo z group by z.name order by cnt desc, zname"
				).list();
		assertEquals( 3, list.size() );
		assertEquals( zoo3.getName(), ( ( Object[] ) list.get( 0 ) )[ 0 ] );
		assertEquals( Long.valueOf( 2 ), ( ( Object[] ) list.get( 0 ) )[ 1 ] );
		assertEquals( zoo2.getName(), ( ( Object[] ) list.get( 1 ) )[ 0 ] );
		assertEquals( Long.valueOf( 1 ), ( ( Object[] ) list.get( 1 ) )[ 1 ] );
		assertEquals( zoo4.getName(), ( ( Object[] ) list.get( 2 ) )[ 0 ] );
		assertEquals( Long.valueOf( 1 ), ( ( Object[] ) list.get( 2 ) )[ 1 ] );
		t.commit();
		s.close();
		cleanupData();
	}
