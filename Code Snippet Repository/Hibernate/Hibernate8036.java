	@Test
	public void testFloatCasting() {
		Session s = openSession();
		s.beginTransaction();

		// using the short name (also the primitive name)
		s.createQuery( "select cast(e.theLostNumber as float) from MyEntity e" ).list();
		// using the java class name
		s.createQuery( "select cast(e.theLostNumber as java.lang.Float) from MyEntity e" ).list();
		// using the fqn Hibernate Type name
		s.createQuery( "select cast(e.theLostNumber as org.hibernate.type.FloatType) from MyEntity e" ).list();

		s.getTransaction().commit();
		s.close();
	}
