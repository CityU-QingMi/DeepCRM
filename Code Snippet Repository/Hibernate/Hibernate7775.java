	@Test
	public void testOrphanDelete() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User gavin = new User("gavin", "secret");
		Document hia = new Document("HiA", "blah blah blah", gavin);
		Document hia2 = new Document("HiA2", "blah blah blah blah", gavin);
		s.persist(gavin);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		gavin = (User) s.get(User.class, "gavin");
		assertEquals( 2, gavin.getDocuments().size() );
		gavin.getDocuments().remove(hia2);
		assertFalse( gavin.getDocuments().contains(hia2) );
		assertTrue( gavin.getDocuments().contains(hia) );
		assertEquals( 1, gavin.getDocuments().size() );
		assertFalse( Hibernate.isInitialized( gavin.getDocuments() ) );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		gavin = (User) s.get(User.class, "gavin");
		assertEquals( 1, gavin.getDocuments().size() );
		assertFalse( gavin.getDocuments().contains(hia2) );
		assertTrue( gavin.getDocuments().contains(hia) );
		assertFalse( Hibernate.isInitialized( gavin.getDocuments() ) );
		assertNull( s.get(Document.class, "HiA2") );
		gavin.getDocuments().clear();
		assertTrue( Hibernate.isInitialized( gavin.getDocuments() ) );
		s.delete(gavin);
		t.commit();
		s.close();
	}
