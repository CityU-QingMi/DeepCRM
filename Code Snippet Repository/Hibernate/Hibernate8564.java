	@Test
	public void testAutosaveChildren() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		Set bars = new HashSet();
		baz.setCascadingBars(bars);
		s.save(baz);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		baz.getCascadingBars().add( new Bar() );
		baz.getCascadingBars().add( new Bar() );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		assertTrue( baz.getCascadingBars().size()==2 );
		assertTrue( baz.getCascadingBars().iterator().next()!=null );
		baz.getCascadingBars().clear(); //test all-delete-orphan;
		s.flush();
		assertTrue( s.createQuery( "from Bar bar" ).list().size()==0 );
		s.delete(baz);
		t.commit();
		s.close();
	}
