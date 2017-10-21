	@Test
	@SuppressWarnings( {""})
	public void testOrphanDeleteOnMergeRemoveElementMerge() {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		Product prod = new Product();
		prod.setName( "Widget" );
		Part part = new Part();
		part.setName("Widge");
		part.setDescription("part if a Widget");
		prod.getParts().add(part);
		session.persist(prod);
		t.commit();
		session.close();

		session = openSession();
		t = session.beginTransaction();
		session.merge(prod);
		prod.getParts().remove( part );
		session.merge( prod );
		t.commit();
		session.close();

		session = openSession();
		t = session.beginTransaction();
		assertNull( session.get( Part.class, "Widge" ) );
		session.delete( session.get(Product.class, "Widget") );
		t.commit();
		session.close();
	}
