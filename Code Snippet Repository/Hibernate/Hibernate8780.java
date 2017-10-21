	@Test
	public void testIndexFunctionOnManyToManyMap() {
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "from Group g join g.users u where g.name = 'something' and index(u) = 'nada'" )
				.list();
		s.createQuery( "from Group g join g.users u where g.name = 'something' and minindex(u) = 'nada'" )
				.list();
		s.createQuery( "from Group g join g.users u where g.name = 'something' and maxindex(u) = 'nada'" )
				.list();
		s.getTransaction().commit();
		s.close();
	}
