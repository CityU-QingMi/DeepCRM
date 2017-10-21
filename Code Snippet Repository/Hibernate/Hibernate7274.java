	@Test
	public void testOrphanDelete() {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		Product prod = new Product( "Widget" );
		Part part = new Part( "Widge", "part if a Widget" );
		MapKey mapKey = new MapKey( "Top" );
		prod.getParts().put( mapKey, part );
		Part part2 = new Part( "Get", "another part if a Widget" );
		prod.getParts().put( new MapKey( "Bottom" ), part2 );
		session.persist( prod );
		t.commit();
		session.close();

		sessionFactory().getCache().evictEntityRegion(Product.class);
		sessionFactory().getCache().evictEntityRegion(Part.class);

		session = openSession();
		t = session.beginTransaction();
		prod = (Product) session.get(Product.class, "Widget");
		assertTrue( Hibernate.isInitialized( prod.getParts() ) );
		part = (Part) session.get(Part.class, "Widge");
		prod.getParts().remove(mapKey);
		t.commit();
		session.close();

		sessionFactory().getCache().evictEntityRegion( Product.class );
		sessionFactory().getCache().evictEntityRegion(Part.class);

		session = openSession();
		t = session.beginTransaction();
		prod = (Product) session.get(Product.class, "Widget");
		assertTrue( Hibernate.isInitialized( prod.getParts() ) );
		assertNull( prod.getParts().get(new MapKey("Top")));
		assertNotNull( session.get(Part.class, "Get") );
		session.delete( session.get(Product.class, "Widget") );
		t.commit();
		session.close();
	}
