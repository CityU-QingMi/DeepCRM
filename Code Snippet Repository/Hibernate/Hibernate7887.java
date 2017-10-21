	@Test(timeout = 5 * 60 * 1000)
	public void testOrderBySelectNewMapArgAliasRef() {
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
						"select new map( z.name as zname, z.address as zaddress ) from Zoo z left join z.mammals m order by zname, zaddress"
				).list();
		assertEquals( 4, list.size() );
		assertEquals( zoo2.getName(), ( ( Map ) list.get( 0 ) ).get( "zname" ) );
		assertEquals( zoo2.getAddress(), ( ( Map ) list.get( 0 ) ).get( "zaddress" ) );
		assertEquals( zoo4.getName(), ( ( Map ) list.get( 1 ) ).get( "zname" ) );
		assertEquals( zoo4.getAddress(), ( ( Map ) list.get( 1 ) ).get( "zaddress" ) );
		assertEquals( zoo3.getName(), ( ( Map ) list.get( 2 ) ).get( "zname" ) );
		assertEquals( zoo3.getAddress(), ( ( Map ) list.get( 2 ) ).get( "zaddress" ) );
		assertEquals( zoo1.getName(), ( ( Map ) list.get( 3 ) ).get( "zname" ) );
		assertEquals( zoo1.getAddress(), ( ( Map ) list.get( 3 ) ).get( "zaddress" ) );

		// ordered by address, name:
		//   zoo3  Zoo         1312 Mockingbird Lane, Anywhere, IL USA
		//   zoo4  Duh Zoo     1312 Mockingbird Lane, Nowhere, IL USA
		//   zoo2  A Zoo       1313 Mockingbird Lane, Anywhere, IL USA
		//   zoo1  Zoo         1313 Mockingbird Lane, Anywhere, IL USA
		list =
				s.createQuery(
						"select new map( z.name as zname, z.address as zaddress ) from Zoo z left join z.mammals m order by zaddress, zname"
				).list();
		assertEquals( 4, list.size() );
		assertEquals( zoo3.getName(), ( ( Map ) list.get( 0 ) ).get( "zname" ) );
		assertEquals( zoo3.getAddress(), ( ( Map ) list.get( 0 ) ).get( "zaddress" ) );
		assertEquals( zoo4.getName(), ( ( Map ) list.get( 1 ) ).get( "zname" ) );
		assertEquals( zoo4.getAddress(), ( ( Map ) list.get( 1 ) ).get( "zaddress" ) );
		assertEquals( zoo2.getName(), ( ( Map ) list.get( 2 ) ).get( "zname" ) );
		assertEquals( zoo2.getAddress(), ( ( Map ) list.get( 2 ) ).get( "zaddress" ) );
		assertEquals( zoo1.getName(), ( ( Map ) list.get( 3 ) ).get( "zname" ) );
		assertEquals( zoo1.getAddress(), ( ( Map ) list.get( 3 ) ).get( "zaddress" ) );
		t.commit();
		s.close();

		cleanupData();
	}
