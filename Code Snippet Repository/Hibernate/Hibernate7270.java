	@Test
	public void testOrphanDeleteAfterPersistAndFlush() {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		Product prod = new Product( "Widget" );
		Part part = new Part( "Widge", "part if a Widget" );
		MapKey mapKey = new MapKey( "Top" );
		prod.getParts().put( mapKey, part );
		Part part2 = new Part( "Get", "another part if a Widget" );
		prod.getParts().put( new MapKey( "Bottom" ), part2 );
		session.persist( prod );
		session.flush();

		prod.getParts().remove( mapKey );

		t.commit();
		session.close();

		session = openSession();
		t = session.beginTransaction();
		assertNull( session.get(Part.class, "Widge") );
		assertNotNull( session.get(Part.class, "Get") );
		session.delete( session.get(Product.class, "Widget") );
		t.commit();
		session.close();
	}
