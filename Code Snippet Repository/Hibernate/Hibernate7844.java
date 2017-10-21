	@Test
	public void testBasicFilteredHqlDelete() {
		Session s = openSession();
		s.beginTransaction();
		s.save( new Person( "Steve", 'M' ) );
		s.save( new Person( "Emmanuel", 'M' ) );
		s.save( new Person( "Gail", 'F' ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.enableFilter( "sex" ).setParameter( "sexCode", new Character( 'M' ) );
		int count = s.createQuery( "delete Person" ).executeUpdate();
		assertEquals( 2, count );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete Person" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
