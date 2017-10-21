	@Test
	public void testDeclaredValues() {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Country c = new Country();
		c.setName( "France" );
		AmericaCupClass f = new AmericaCupClass();
		f.setSize( 2 );
		f.setCountry( c );
		s.persist( c );
		s.persist( f );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		f = (AmericaCupClass) s.get( AmericaCupClass.class, f.getId() );
		assertNotNull( f );
		assertEquals( c, f.getCountry() );
		assertEquals( 2, f.getSize() );
		s.delete( f );
		s.delete( f.getCountry() );
		tx.commit();
		s.close();
	}
