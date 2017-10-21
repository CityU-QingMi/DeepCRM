	@Test
	public void testBagOneToMany() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		List list = new ArrayList();
		baz.setBazez(list);
		list.add( new Baz() );
		s.save(baz);
		s.flush();
		list.add( new Baz() );
		s.flush();
		list.add( 0, new Baz() );
		s.flush();
		s.delete( list.remove(1) );
		s.flush();
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
