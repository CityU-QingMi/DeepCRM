	@Test
	@SuppressWarnings( {""})
	@TestForIssue(jiraKey = "")
	public void testOrphanDeleteOnAddElementMergeRemoveElementMerge() {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		Product prod = new Product();
		prod.setName( "Widget" );
		session.persist(prod);
		t.commit();
		session.close();

		Part part = new Part();
		part.setName("Widge");
		part.setDescription("part if a Widget");
		prod.getParts().add(part);

		session = openSession();
		t = session.beginTransaction();
		session.merge(prod);
		// In Section 2.9, Entity Relationships, the JPA 2.1 spec says:
		// "If the entity being orphaned is a detached, new, or removed entity,
		// the semantics of orphanRemoval do not apply."
		// In other words, since part is a new entity, it will not be deleted when removed
		// from prod.parts, even though cascade for the association includes "delete-orphan".
		prod.getParts().remove(part);
		session.merge( prod );
		t.commit();
		session.close();

		session = openSession();
		t = session.beginTransaction();
		assertNotNull( session.get( Part.class, "Widge" ) );
		session.delete( session.get(Product.class, "Widget") );
		t.commit();
		session.close();
	}
