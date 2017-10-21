	public void testCRUD() throws HibernateException, SQLException {
		Person p = new Person();
		p.setName("Max");
		p.setLastName("Andersen");
		p.setNationalID("110974XYZ");
		p.setAddress("P. P. Street 8");

		Session s = openSession();
		s.beginTransaction();
		s.save(p);
		s.getTransaction().commit();
		s.close();

		sessionFactory().getCache().evictEntityRegion( Person.class );

		s = openSession();
		s.beginTransaction();
		Person p2 = (Person) s.get(Person.class, p.getId());
		assertNotSame(p, p2);
		assertEquals(p2.getId(),p.getId());
		assertEquals(p2.getLastName(),p.getLastName());
		s.flush();

		List list = s.createQuery( "select p from Party as p" ).list();
		assertTrue(list.size() == 1);

		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		list = s.createQuery( "select p from Person as p where p.address = 'Lrkevnget 1'" ).list();
		assertTrue(list.size() == 0);
		p.setAddress("Lrkevnget 1");
		s.update(p);
		list = s.createQuery( "select p from Person as p where p.address = 'Lrkevnget 1'" ).list();
		assertTrue(list.size() == 1);
		list = s.createQuery( "select p from Party as p where p.address = 'P. P. Street 8'" ).list();
		assertTrue(list.size() == 0);

		s.delete(p);
		list = s.createQuery( "select p from Person as p" ).list();
		assertTrue(list.size() == 0);

		s.getTransaction().commit();
		s.close();
	}
