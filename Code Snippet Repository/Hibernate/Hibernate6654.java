	@Test
	@SuppressWarnings({ "" })
	public void testSingleGeneratedValue() {
		Session s = openSession();
		s.beginTransaction();
		Simple2 s1 = new Simple2( 200L, 10 );
		s.persist( s1 );
		Long s1Id1 = s1.getId1();
		Simple2 s2 = new Simple2( 300L, 20 );
		s.persist( s2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List<Simple2> simpleList = s.createQuery( "select s from Simple2 s" ).list();
		assertEquals( simpleList.size(), 2 );
		s1 = ( Simple2 ) s.load( Simple2.class, new SimplePK( s1Id1, 200L ) );
		assertEquals( s1.getQuantity(), 10 );
		s.clear();
		s.createQuery( "delete Simple2" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
