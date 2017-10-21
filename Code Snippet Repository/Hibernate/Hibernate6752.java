	@Test
	public void testLobInsertUpdateDeleteSelect() {
		Session session = openSession();

		// Insert
		session.getTransaction().begin();
		Document document = new Document( 1, "HHH-8103", "Oracle expects all LOB properties to be last in INSERT and UPDATE statements." );
		session.persist( document );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		Assert.assertEquals( document, session.get( Document.class, document.getId() ) );
		session.getTransaction().commit();

		session.clear();

		// Update
		session.getTransaction().begin();
		document = (Document) session.get( Document.class, document.getId() );
		document.setFullText( "Correct!" );
		session.update( document );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		Assert.assertEquals( document, session.get( Document.class, document.getId() ) );
		session.getTransaction().commit();

		session.clear();

		// Delete
		session.getTransaction().begin();
		document = (Document) session.get( Document.class, document.getId() );
		session.delete( document );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		Assert.assertNull( session.get( Document.class, document.getId() ) );
		session.getTransaction().commit();

		session.close();
	}
