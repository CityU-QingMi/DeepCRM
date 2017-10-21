	private void check() {
		Session s = openSession();
		s.getTransaction().begin();
		EntityB bRead = (EntityB) s.get( EntityB.class, b.getId() );
		Assert.assertEquals( b, bRead );

		EntityG gRead = bRead.getGCollection().iterator().next();
		Assert.assertEquals( g, gRead );
		EntityC cRead = bRead.getC();
		Assert.assertEquals( c, cRead );
		EntityD dRead = bRead.getD();
		Assert.assertEquals( d, dRead );

		Assert.assertSame( bRead, cRead.getBCollection().iterator().next() );
		Assert.assertSame( dRead, cRead.getDCollection().iterator().next() );

		Assert.assertSame( bRead, dRead.getBCollection().iterator().next() );
		Assert.assertEquals( cRead, dRead.getC() );
		EntityE eRead = dRead.getE();
		Assert.assertEquals( e, eRead );
		EntityF fRead = dRead.getFCollection().iterator().next();
		Assert.assertEquals( f, fRead );

		Assert.assertSame( dRead, eRead.getDCollection().iterator().next() );
		Assert.assertSame( fRead, eRead.getF() );

		Assert.assertSame( eRead, fRead.getECollection().iterator().next() );
		Assert.assertSame( dRead, fRead.getD() );
		Assert.assertSame( gRead, fRead.getG());

		Assert.assertSame( bRead, gRead.getB() );
		Assert.assertSame( fRead, gRead.getFCollection().iterator().next() );

		s.getTransaction().commit();
		s.close();
	}
