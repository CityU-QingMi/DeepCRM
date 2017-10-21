	@Test
	public void testRemoveContains() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		baz.setDefaults();
		s.save( baz );
		s.flush();
		assertTrue( s.contains(baz) );
		s.evict( baz );
		assertFalse( s.contains(baz) );
		Baz baz2 = (Baz) s.load( Baz.class, baz.getCode() );
		assertFalse( baz == baz2 );
		s.delete(baz2);
		s.getTransaction().commit();
		s.close();
	}
