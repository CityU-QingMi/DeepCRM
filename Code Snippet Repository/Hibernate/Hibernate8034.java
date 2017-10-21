	@Test
	public void testIntegerCasting() {
		Session s = openSession();
		s.beginTransaction();

		// using the short name
		s.createQuery( "select cast(e.theLostNumber as integer) from MyEntity e" ).list();
		// using the java class name (primitive)
		s.createQuery( "select cast(e.theLostNumber as int) from MyEntity e" ).list();
		// using the java class name
		s.createQuery( "select cast(e.theLostNumber as java.lang.Integer) from MyEntity e" ).list();
		// using the fqn Hibernate Type name
		s.createQuery( "select cast(e.theLostNumber as org.hibernate.type.IntegerType) from MyEntity e" ).list();

		s.getTransaction().commit();
		s.close();
	}
