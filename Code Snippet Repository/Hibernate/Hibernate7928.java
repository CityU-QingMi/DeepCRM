	@Test
	public void testNakedComponentPropertyRef() {
		// note: simply performing syntax and column/table resolution checking in the db
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "from Human where name.first = 'Gavin'" ).list();
		s.createQuery( "select name from Human" ).list();
		s.createQuery( "select upper(h.name.first) from Human as h" ).list();
		s.createQuery( "select upper(name.first) from Human" ).list();
		s.getTransaction().commit();
		s.close();
	}
