	@Test
	@SuppressWarnings({ "" })
	public void testBaseLine() {
		Session s = openSession();
		s.beginTransaction();
		Simple s1 = new Simple( 1L, 2L, 10 );
		s.persist( s1 );
		Simple s2 = new Simple( 2L, 3L, 20 );
		s.persist( s2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List<Simple> simpleList = s.createQuery( "select s from Simple s" ).list();
		assertEquals( simpleList.size(), 2 );
		s1 = ( Simple ) s.load( Simple.class, new SimplePK( 1L, 2L ) );
		assertEquals( s1.getQuantity(), 10 );
		s.clear();
		s.createQuery( "delete Simple" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
