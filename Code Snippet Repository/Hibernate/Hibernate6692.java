	@Test
	public void testFkList() throws Exception {
		Wardrobe w = new Wardrobe();
		Drawer d1 = new Drawer();
		Drawer d2 = new Drawer();
		w.setDrawers( new ArrayList<Drawer>() );
		w.getDrawers().add( d1 );
		w.getDrawers().add( d2 );
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( w );

		s.flush();
		s.clear();

		w = (Wardrobe) s.get( Wardrobe.class, w.getId() );
		assertNotNull( w );
		assertNotNull( w.getDrawers() );
		List<Drawer> result = w.getDrawers();
		assertEquals( 2, result.size() );
		assertEquals( d2.getId(), result.get( 1 ).getId() );
		result.remove( d1 );
		s.flush();
		d1 = (Drawer) s.merge( d1 );
		result.add( d1 );

		s.flush();
		s.clear();

		w = (Wardrobe) s.get( Wardrobe.class, w.getId() );
		assertNotNull( w );
		assertNotNull( w.getDrawers() );
		result = w.getDrawers();
		assertEquals( 2, result.size() );
		assertEquals( d1.getId(), result.get( 1 ).getId() );
		s.delete( result.get( 0 ) );
		s.delete( result.get( 1 ) );
		s.delete( w );
		s.flush();
		tx.rollback();
		s.close();
	}
