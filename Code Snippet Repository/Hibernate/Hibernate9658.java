	@Test
	public void testCollectionNoVersion() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Person gavin = new Person("Gavin");
		new Task("Code", gavin);
		s.persist(gavin);
		t.commit();
		s.close();
		
		assertEquals(0, gavin.getVersion());
		
		s = openSession();
		t = s.beginTransaction();
		gavin = (Person) s.createCriteria(Person.class).uniqueResult();
		new Task("Document", gavin);
		t.commit();
		s.close();
		
		assertEquals(0, gavin.getVersion());
		assertFalse( Hibernate.isInitialized( gavin.getTasks() ) );

		s = openSession();
		t = s.beginTransaction();
		gavin = (Person) s.createCriteria(Person.class).uniqueResult();
		gavin.getTasks().clear();
		t.commit();
		s.close();
		
		assertEquals(0, gavin.getVersion());
		assertTrue( Hibernate.isInitialized( gavin.getTasks() ) );

		s = openSession();
		t = s.beginTransaction();
		s.delete(gavin);
		t.commit();
		s.close();
	}
