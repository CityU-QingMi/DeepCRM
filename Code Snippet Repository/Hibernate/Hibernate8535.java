	@Test
	public void testPersistentLifecycle() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Qux q = new Qux();
		s.save(q);
		q.setStuff("foo bar baz qux");
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		q = (Qux) s.load( Qux.class, q.getKey() );
		assertTrue( "lifecycle create", q.getCreated() );
		assertTrue( "lifecycle load", q.getLoaded() );
		assertTrue( "lifecycle subobject", q.getFoo()!=null );
		s.delete(q);
		assertTrue( "lifecycle delete", q.getDeleted() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		assertTrue( "subdeletion", s.createQuery( "from Foo foo" ).list().size()==0);
		s.getTransaction().commit();
		s.close();
	}
