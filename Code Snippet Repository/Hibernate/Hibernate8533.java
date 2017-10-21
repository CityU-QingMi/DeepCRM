	@Test
	@SuppressWarnings( {""})
	public void testCollectionOfSelf() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Bar bar = new Bar();
		s.save(bar);
		bar.setAbstracts( new HashSet() );
		bar.getAbstracts().add( bar );
		Bar bar2 = new Bar();
		bar.getAbstracts().add( bar2 );
		bar.setFoo(bar);
		s.save( bar2 );
		s.getTransaction().commit();
		s.close();

		bar.setAbstracts( null );

		s = openSession();
		s.beginTransaction();
		s.load( bar, bar.getKey() );
		bar2 = s.load( Bar.class, bar2.getKey() );
		assertTrue( "collection contains self", bar.getAbstracts().size() == 2 && bar.getAbstracts().contains( bar ) );
		assertTrue( "association to self", bar.getFoo()==bar );

		// for MySQL :(
		bar.getAbstracts().clear();
		bar.setFoo( null );
		s.flush();

		s.delete( bar );
		s.delete( bar2 );
		s.getTransaction().commit();
		s.close();
	}
