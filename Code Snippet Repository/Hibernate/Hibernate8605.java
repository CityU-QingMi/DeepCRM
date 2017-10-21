	@Test
	public void testCustomPersister() throws Exception {
		Session s = openSession();
		Custom c = new Custom();
		c.setName( "foo" );
		c.id="100";
		s.beginTransaction();
		String id = id = (String) s.save( c );
		assertTrue( c == s.load( Custom.class, id ) );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		c = (Custom) s.load(Custom.class, id);
		assertTrue( c.getName().equals("foo") );
		c.setName( "bar" );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		c = (Custom) s.load(Custom.class, id);
		assertTrue( c.getName().equals("bar") );
		s.delete(c);
		s.flush();
		s.getTransaction().commit();
		s.close();
		s = openSession();
		boolean none = false;
		try {
			s.load(Custom.class, id);
		}
		catch (ObjectNotFoundException onfe) {
			none=true;
		}
		assertTrue(none);
		s.close();
	}
