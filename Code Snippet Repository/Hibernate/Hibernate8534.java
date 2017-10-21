	@Test
	public void testDeleteRecursive() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Foo x = new Foo();
		Foo y = new Foo();
		x.setFoo( y );
		y.setFoo( x );
		s.save( x );
		s.save( y );
		s.flush();
		s.delete( y );
		s.delete( x );
		s.getTransaction().commit();
		s.close();
	}
