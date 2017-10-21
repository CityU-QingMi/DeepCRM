	@Test
	public void testMixNativeAssigned() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Category c = new Category();
		c.setName("NAME");
		Assignable assn = new Assignable();
		assn.setId("i.d.");
		List l = new ArrayList();
		l.add( c );
		assn.setCategories( l );
		c.setAssignable( assn );
		s.save( assn );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( assn );
		s.getTransaction().commit();
		s.close();
	}
