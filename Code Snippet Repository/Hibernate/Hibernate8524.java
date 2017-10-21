	@Test
	public void testCascadeSave() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		List list = new ArrayList();
		list.add( new Fee() );
		list.add( new Fee() );
		baz.setFees( list );
		s.save(baz);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		assertTrue( baz.getFees().size() == 2 );
		s.delete(baz);
		assertTrue( !s.createQuery( "from Fee fee" ).iterate().hasNext() );
		t.commit();
		s.close();
	}
