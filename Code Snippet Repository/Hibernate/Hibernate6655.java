	@Test
	@SuppressWarnings({ "" })
	public void testMultipleGeneratedValue() {
		Session s = openSession();
		s.beginTransaction();
		Multiple m1 = new Multiple( 1000L, 10 );
		s.persist( m1 );
		Long m1Id1 = m1.getId1();
		Long m1Id2 = m1.getId2();
		Multiple m2 = new Multiple( 2000L, 20 );
		s.persist( m2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List<Multiple> simpleList = s.createQuery( "select m from Multiple m" ).list();
		assertEquals( simpleList.size(), 2 );
		m1 = ( Multiple ) s.load( Multiple.class, new MultiplePK( m1Id1, m1Id2, 1000L ) );
		assertEquals( m1.getQuantity(), 10 );
		s.clear();
		s.createQuery( "delete Multiple" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
