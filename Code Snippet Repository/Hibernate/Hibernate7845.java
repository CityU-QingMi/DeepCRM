	@Test
	public void testBasicFilteredHqlUpdate() {
		Session s = openSession();
		s.beginTransaction();
		s.save( new Person( "Shawn", 'M' ) );
		s.save( new Person( "Sally", 'F' ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.enableFilter( "sex" ).setParameter( "sexCode", new Character( 'M' ) );
		int count = s.createQuery( "update Person p set p.name = 'Shawn'" ).executeUpdate();
		assertEquals( 1, count );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete Person" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
