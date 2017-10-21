	public void testOnCascadeDelete() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		baz.subs = new ArrayList();
		Baz sub = new Baz();
		sub.superBaz = baz;
		baz.subs.add(sub);
		s.save(baz);
		s.flush();
		assertTrue( s.createQuery("from Baz").list().size()==2 );
		s.getTransaction().commit();
		s.beginTransaction();
		s.delete(baz);
		s.getTransaction().commit();
		s.beginTransaction();
		assertTrue( s.createQuery("from Baz").list().size()==0 );
		s.getTransaction().commit();
		s.close();
	}
