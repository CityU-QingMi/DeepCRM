	@Test
	@SuppressWarnings( {""})
	public void testOrphanDeleteAfterLock() {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		Product prod = new Product();
		prod.setName("Widget");
		Part part = new Part();
		part.setName("Widge");
		part.setDescription("part if a Widget");
		prod.getParts().add(part);
		Part part2 = new Part();
		part2.setName("Get");
		part2.setDescription("another part if a Widget");
		prod.getParts().add(part2);
		session.persist(prod);
		t.commit();
		session.close();
		
		
		session = openSession();
		t = session.beginTransaction();
		session.lock( prod, LockMode.READ );
		prod.getParts().remove(part);
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
