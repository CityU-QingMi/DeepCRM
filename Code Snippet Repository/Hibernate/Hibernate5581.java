	private void check() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		B bRead = em.find( B.class, b.getId() );
		Assert.assertEquals( b, bRead );

		G gRead = bRead.getGCollection().iterator().next();
		Assert.assertEquals( g, gRead );
		C cRead = bRead.getC();
		Assert.assertEquals( c, cRead );
		D dRead = bRead.getD();
		Assert.assertEquals( d, dRead );

		Assert.assertSame( bRead, cRead.getBCollection().iterator().next() );
		Assert.assertSame( dRead, cRead.getDCollection().iterator().next() );

		Assert.assertSame( bRead, dRead.getBCollection().iterator().next() );
		Assert.assertEquals( cRead, dRead.getC() );
		E eRead = dRead.getE();
		Assert.assertEquals( e, eRead );
		F fRead = dRead.getFCollection().iterator().next();
		Assert.assertEquals( f, fRead );

		Assert.assertSame( dRead, eRead.getDCollection().iterator().next() );
		Assert.assertSame( fRead, eRead.getF() );

		Assert.assertSame( eRead, fRead.getECollection().iterator().next() );
		Assert.assertSame( dRead, fRead.getD() );
		Assert.assertSame( gRead, fRead.getG());

		Assert.assertSame( bRead, gRead.getB() );
		Assert.assertSame( fRead, gRead.getFCollection().iterator().next() );

		em.getTransaction().commit();
		em.close();
	}
