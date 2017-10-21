	@Test
	public void testLateCollectionAdd() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		List l = new ArrayList();
		baz.setStringList(l);
		l.add( "foo" );
		Serializable id = s.save(baz);
		l.add("bar");
		s.flush();
		l.add( "baz" );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.load(Baz.class, id);
		assertTrue( baz.getStringList().size() == 3 && baz.getStringList().contains( "bar" ) );
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
