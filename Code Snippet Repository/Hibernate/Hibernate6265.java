	@Test
	public void testCompositePk() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Carrot c = new Carrot();
		VegetablePk pk = new VegetablePk();
		pk.setFarmer( "Bill" );
		pk.setHarvestDate( "2004-08-15" );
		c.setId( pk );
		c.setLength( 23 );
		s.persist( c );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Vegetable v = (Vegetable) s.createCriteria( Vegetable.class ).uniqueResult();
		assertTrue( v instanceof Carrot );
		Carrot result = (Carrot) v;
		assertEquals( 23, result.getLength() );
		tx.commit();
		s.close();
	}
