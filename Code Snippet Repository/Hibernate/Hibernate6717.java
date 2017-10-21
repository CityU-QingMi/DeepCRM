	@Test
	public void testSecondaryTableAndJoined() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		SwimmingPool sp = new SwimmingPool();
		s.persist( sp );
		s.flush();
		s.clear();

		long rowCount = getTableRowCount( s );
		assertEquals(
				"The address table is marked as optional. For null values no database row should be created",
				0,
				rowCount
		);

		SwimmingPool sp2 = (SwimmingPool) s.get( SwimmingPool.class, sp.getId() );
		assertEquals( sp.getAddress(), null );

		PoolAddress address = new PoolAddress();
		address.setAddress( "Park Avenue" );
		sp2.setAddress( address );

		s.flush();
		s.clear();

		sp2 = (SwimmingPool) s.get( SwimmingPool.class, sp.getId() );
		rowCount = getTableRowCount( s );
		assertEquals(
				"Now we should have a row in the pool address table ",
				1,
				rowCount
		);
		assertFalse( sp2.getAddress() == null );
		assertEquals( sp2.getAddress().getAddress(), "Park Avenue" );

		tx.rollback();
		s.close();
	}
