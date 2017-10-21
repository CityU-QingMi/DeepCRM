	@Test
	public void testOneToOneEmbeddedCompositeKey() {
		Person p = new Person();
		p.setName("Gavin King");
		Address a = new Address();
		a.setPerson(p);
		a.setType("HOME");
		a.setZip("3181");
		a.setState("VIC");
		a.setStreet("Karbarook Ave");
		p.setAddress(a);
		
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist(p);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		
		a = new Address();
		a.setType("HOME");
		a.setPerson(p);
		a = (Address) s.load(Address.class, a);
		assertFalse( Hibernate.isInitialized(a) );
		a.getPerson();
		a.getType();
		assertFalse( Hibernate.isInitialized(a) );
		assertEquals(a.getZip(), "3181");
		
		s.clear();
		
		a = new Address();
		a.setType("HOME");
		a.setPerson(p);
		Address a2 = (Address) s.get(Address.class, a);
		assertTrue( Hibernate.isInitialized(a) );
		assertSame(a2, a);
		assertSame(a2.getPerson(), p); //this is a little bit desirable
		assertEquals(a.getZip(), "3181");
		
		s.delete(a2);
		s.delete( s.get( Person.class, p.getName() ) ); //this is certainly undesirable! oh well...
		
		t.commit();
		s.close();
		
	}
