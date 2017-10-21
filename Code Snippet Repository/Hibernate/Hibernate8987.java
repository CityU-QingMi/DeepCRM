	@Test
	@SuppressWarnings( {""})
	public void testOrphanDeleteOnDelete() {
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
		session.flush();
		
		prod.getParts().remove(part);
		
		session.delete(prod);
		
		t.commit();
		session.close();
		
		session = openSession();
		t = session.beginTransaction();
		assertNull( session.get(Part.class, "Widge") );
		assertNull( session.get(Part.class, "Get") );
		assertNull( session.get(Product.class, "Widget") );
		t.commit();
		session.close();
	}
