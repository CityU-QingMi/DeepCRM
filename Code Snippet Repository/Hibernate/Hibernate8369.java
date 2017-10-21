	@Test
	public void testSequentialSelectsOptionalData() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		User jesus = new User();
		jesus.setName("Jesus Olvera y Martinez");
		jesus.setSex('M');

		s.save(jesus);

		assertEquals( s.createQuery("from java.io.Serializable").list().size(), 0 );
		
		assertEquals( s.createQuery("from Person").list().size(), 1 );
		assertEquals( s.createQuery("from Person p where p.class is null").list().size(), 0 );
		assertEquals( s.createQuery("from Person p where p.class = User").list().size(), 1 );
		assertTrue(s.createQuery("from User u").list().size()==1);
		s.clear();

		// Remove the optional row from the join table and requery the User obj
		doWork(s);
		s.clear();

		jesus = (User) s.get( Person.class, new Long( jesus.getId() ) );
		s.clear();

		// Cleanup the test data
		s.delete(jesus);

		assertTrue( s.createQuery("from Person").list().isEmpty() );
		t.commit();
		s.close();
	}
