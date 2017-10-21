	@Test
	public void testSaveAddDelete() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		Set bars = new HashSet();
		baz.setCascadingBars( bars );
		s.save( baz );
		s.flush();
		baz.getCascadingBars().add( new Bar() );
		s.delete(baz);
		s.flush();
		s.getTransaction().commit();
		s.close();
	}
