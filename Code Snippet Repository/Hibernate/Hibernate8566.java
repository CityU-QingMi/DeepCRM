	@Test
	public void testTransientOrphanDelete() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		Set bars = new HashSet();
		baz.setCascadingBars(bars);
		bars.add( new Bar() );
		bars.add( new Bar() );
		bars.add( new Bar() );
		List foos = new ArrayList();
		foos.add( new Foo() );
		foos.add( new Foo() );
		baz.setFooBag(foos);
		s.save(baz);
		Iterator i = new JoinedIterator( new Iterator[] {foos.iterator(), bars.iterator()} );
		while ( i.hasNext() ) {
			FooComponent cmp = ( (Foo) i.next() ).getComponent();
			s.delete( cmp.getGlarch() );
			cmp.setGlarch(null);
		}
		t.commit();
		s.close();

		bars.remove( bars.iterator().next() );
		foos.remove(1);
		s = openSession();
		t = s.beginTransaction();
		s.update(baz);
		assertEquals( 2, s.createQuery( "From Bar bar" ).list().size() );
		assertEquals( 3, s.createQuery( "From Foo foo" ).list().size() );
		t.commit();
		s.close();

		foos.remove(0);
		s = openSession();
		t = s.beginTransaction();
		s.update(baz);
		bars.remove( bars.iterator().next() );
		assertEquals( 1, s.createQuery( "From Foo foo" ).list().size() );
		s.delete(baz);
		//s.flush();
		assertEquals( 0, s.createQuery( "From Foo foo" ).list().size() );
		t.commit();
		s.close();
	}
