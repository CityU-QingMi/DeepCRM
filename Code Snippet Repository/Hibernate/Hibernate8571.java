	@Test
	public void testWierdSession() throws Exception {
 		Session s = openSession();
 		Transaction t = s.beginTransaction();
 		Serializable id =  s.save( new Foo() );
 		t.commit();
 		s.close();

 		s = openSession();
 		s.setFlushMode(FlushMode.MANUAL);
		t = s.beginTransaction();
		Foo foo = (Foo) s.get(Foo.class, id);
		t.commit();

		t = s.beginTransaction();
		s.flush();
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		foo = (Foo) s.get(Foo.class, id);
		s.delete(foo);
		t.commit();
		s.close();
	}
