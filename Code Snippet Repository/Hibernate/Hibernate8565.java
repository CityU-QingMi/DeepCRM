	@Test
	public void testOrphanDelete() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		Set bars = new HashSet();
		baz.setCascadingBars(bars);
		bars.add( new Bar() );
		bars.add( new Bar() );
		bars.add( new Bar() );
		bars.add( new Bar() );
		s.save(baz);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		bars = baz.getCascadingBars();
		assertEquals( 4, bars.size() );
		bars.remove( bars.iterator().next() );
		assertEquals( 3, s.createQuery( "From Bar bar" ).list().size() );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		bars = baz.getCascadingBars();
		assertEquals( 3, bars.size() );
		bars.remove( bars.iterator().next() );
		s.delete(baz);
		bars.remove( bars.iterator().next() );
		assertEquals( 0, s.createQuery( "From Bar bar" ).list().size() );
		t.commit();
		s.close();
	}
