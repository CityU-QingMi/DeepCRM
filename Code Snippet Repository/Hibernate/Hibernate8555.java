	@Test
	public void testSerializableType() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Vetoer v = new Vetoer();
		v.setStrings( new String[] {"foo", "bar", "baz"} );
		s.save( v ); Serializable id = s.save(v);
		v.getStrings()[1] = "osama";
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		v = (Vetoer) s.load(Vetoer.class, id);
		assertTrue( "serializable type", v.getStrings()[1].equals( "osama" ) );
		s.delete(v); s.delete( v );
		s.flush();
		s.getTransaction().commit();
		s.close();
	}
