	@Test
	public void testLongCasting() {
		Session s = openSession();
		s.beginTransaction();

		// using the short name (also the primitive name)
		s.createQuery( "select cast(e.theLostNumber as long) from MyEntity e" ).list();
		// using the java class name
		s.createQuery( "select cast(e.theLostNumber as java.lang.Long) from MyEntity e" ).list();
		// using the fqn Hibernate Type name
		s.createQuery( "select cast(e.theLostNumber as org.hibernate.type.LongType) from MyEntity e" ).list();

		s.getTransaction().commit();
		s.close();
	}
