	@Test
	public void testReuseDeletedCollection() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		baz.setDefaults();
		s.save(baz);
		s.flush();
		s.delete(baz);
		Baz baz2 = new Baz();
		baz2.setStringArray( new String[] {"x-y-z"} );
		s.save(baz2);
		s.getTransaction().commit();
		s.close();

		baz2.setStringSet( baz.getStringSet() );
		baz2.setStringArray( baz.getStringArray() );
		baz2.setFooArray( baz.getFooArray() );

		s = openSession();
		s.beginTransaction();
		s.update(baz2);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz2 = (Baz) s.load( Baz.class, baz2.getCode() );
		assertTrue( baz2.getStringArray().length==3 );
		assertTrue( baz2.getStringSet().size()==3 );
		s.delete(baz2);
		s.getTransaction().commit();
		s.close();
	}
