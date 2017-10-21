	@Test
	public void testFilteredJoinedSubclassHqlUpdateRoot() {
		Session s = openSession();
		s.beginTransaction();
		s.save( new Employee( "John", 'M', "john", new Date() ) );
		s.save( new Employee( "Jane", 'F', "jane", new Date() ) );
		s.save( new Customer( "Charlie", 'M', "charlie", "Acme" ) );
		s.save( new Customer( "Wanda", 'F', "wanda", "ABC" ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.enableFilter( "sex" ).setParameter( "sexCode", Character.valueOf( 'M' ) );
		int count = s.createQuery( "update Person p set p.name = '<male>'" ).executeUpdate();
		assertEquals( 2, count );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete Person" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
