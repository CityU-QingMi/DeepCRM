	@Test
	public void testWorksWithGenericEmbedded() {
		Session session = openSession();
		session.beginTransaction();
		Classes.Edition<String> edition = new Classes.Edition<String>();
		edition.name = "Second";
		Classes.Book b = new Classes.Book();
		b.edition = edition;
		session.persist( b );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		Classes.Book retrieved = (Classes.Book) session.get( Classes.Book.class, b.id );
		assertEquals( "Second", retrieved.edition.name );
		session.delete( retrieved );
		session.getTransaction().commit();
		session.close();
	}
