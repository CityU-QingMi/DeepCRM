	@Test
	public void testComponentParent() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		BarProxy bar = new Bar();
		bar.setBarComponent( new FooComponent() );
		Baz baz = new Baz();
		baz.setComponents( new FooComponent[] { new FooComponent(), new FooComponent() } );
		s.save(bar);
		s.save(baz);
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		bar = (BarProxy) s.load(Bar.class, bar.getKey());
		s.load(baz, baz.getCode());
		assertTrue( bar.getBarComponent().getParent()==bar );
		assertTrue( baz.getComponents()[0].getBaz()==baz && baz.getComponents()[1].getBaz()==baz );
		s.delete(baz);
		s.delete(bar);
		t.commit();
		s.close();
	}
