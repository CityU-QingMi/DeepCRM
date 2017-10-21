	@Test
	public void testComponentJoins() {
		Session s = openSession();
		s.beginTransaction();
		ComponentContainer root = new ComponentContainer(
				new ComponentContainer.Address(
						"123 Main",
						"Anywhere",
						"USA",
						new ComponentContainer.Address.Zip( 12345, 6789 )
				)
		);
		s.save( root );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List result = s.createQuery( "select a from ComponentContainer c join c.address a" ).list();
		assertEquals( 1, result.size() );
		assertTrue( ComponentContainer.Address.class.isInstance( result.get( 0 ) ) );

		result = s.createQuery( "select a.zip from ComponentContainer c join c.address a" ).list();
		assertEquals( 1, result.size() );
		assertTrue( ComponentContainer.Address.Zip.class.isInstance( result.get( 0 ) ) );

		result = s.createQuery( "select z from ComponentContainer c join c.address a join a.zip z" ).list();
		assertEquals( 1, result.size() );
		assertTrue( ComponentContainer.Address.Zip.class.isInstance( result.get( 0 ) ) );

		result = s.createQuery( "select z.code from ComponentContainer c join c.address a join a.zip z" ).list();
		assertEquals( 1, result.size() );
		assertTrue( Integer.class.isInstance( result.get( 0 ) ) );
		s.delete( root );
		s.getTransaction().commit();
		s.close();
	}
