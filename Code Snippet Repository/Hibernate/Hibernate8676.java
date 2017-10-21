	@Test
	@SkipForDialect( MySQLDialect.class )
	public void testPropertyResultSQL() throws HibernateException, SQLException {
		Session s = openSession();
		s.beginTransaction();
		for ( Object entity : s.createQuery( "from Assignable" ).list() ) {
			s.delete( entity );
		}
		for ( Object entity : s.createQuery( "from Category" ).list() ) {
			s.delete( entity );
		}

		Category c = new Category();
		c.setName("NAME");
		Assignable assn = new Assignable();
		assn.setId("i.d.");
		List l = new ArrayList();
		l.add(c);
		assn.setCategories(l);
		c.setAssignable(assn);
		s.save(assn);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Query query = s.getNamedQuery("nonaliasedsql");
		assertNotNull(query);
		List list = query.list();
        assertNotNull(list);
		assertTrue(list.get(0) instanceof Category);
		s.getTransaction().commit();
		s.close();

	}
