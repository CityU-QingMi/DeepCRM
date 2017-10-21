	@Test
	public void testOrderBySelectNewArgAliasRef() {
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
						"select new Zoo( z.name as zname, z.address as zaddress) from Zoo z order by zname, zaddress"
				).list();
		assertEquals( 4, list.size() );
		assertEquals( zoo2, list.get( 0 ) );
		assertEquals( zoo4, list.get( 1 ) );
		assertEquals( zoo3, list.get( 2 ) );
		assertEquals( zoo1, list.get( 3 ) );

		// ordered by address, name:
		//   zoo3  Zoo         1312 Mockingbird Lane, Anywhere, IL USA
		//   zoo4  Duh Zoo     1312 Mockingbird Lane, Nowhere, IL USA
		//   zoo2  A Zoo       1313 Mockingbird Lane, Anywhere, IL USA
		//   zoo1  Zoo         1313 Mockingbird Lane, Anywhere, IL USA
		list =
				s.createQuery(
						"select new Zoo( z.name as zname, z.address as zaddress) from Zoo z order by zaddress, zname"
				).list();
		assertEquals( 4, list.size() );
		assertEquals( zoo3, list.get( 0 ) );
		assertEquals( zoo4, list.get( 1 ) );
		assertEquals( zoo2, list.get( 2 ) );
		assertEquals( zoo1, list.get( 3 ) );


		t.commit();
		s.close();

		cleanupData();
	}
