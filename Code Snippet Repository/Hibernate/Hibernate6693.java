	@Test
	public void testJoinedTableList() throws Exception {
		Wardrobe w = new Wardrobe();
		w.setDrawers( new ArrayList<Drawer>() );
		Drawer d = new Drawer();
		w.getDrawers().add( d );
		Dress d1 = new Dress();
		Dress d2 = new Dress();
		d.setDresses( new ArrayList<Dress>() );
		d.getDresses().add( d1 );
		d.getDresses().add( d2 );
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( d1 );
		s.persist( d2 );
		s.persist( w );

		s.flush();
		s.clear();

		d = (Drawer) s.get( Drawer.class, d.getId() );
		assertNotNull( d );
		assertNotNull( d.getDresses() );
		List<Dress> result = d.getDresses();
		assertEquals( 2, result.size() );
		assertEquals( d2.getId(), result.get( 1 ).getId() );
		result.remove( d1 );
		s.flush();
		d1 = (Dress) s.merge( d1 );
		result.add( d1 );

		s.flush();
		s.clear();

		d = (Drawer) s.get( Drawer.class, d.getId() );
		assertNotNull( d );
		assertNotNull( d.getDresses() );
		result = d.getDresses();
		assertEquals( 2, result.size() );
		assertEquals( d1.getId(), result.get( 1 ).getId() );
		s.delete( result.get( 0 ) );
		s.delete( result.get( 1 ) );
		s.delete( d );
		s.flush();
		tx.rollback();
		s.close();
	}
