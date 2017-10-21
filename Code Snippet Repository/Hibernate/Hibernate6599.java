	public void testWorksWithGenericCollectionOfElements() {
		Session session = openSession();
		session.beginTransaction();
		Classes.Edition<String> edition = new Classes.Edition<String>();
		edition.name = "Second";
		Classes.PopularBook b = new Classes.PopularBook();
		b.editions.add( edition );
		session.persist( b );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		Classes.PopularBook retrieved = (Classes.PopularBook) session.get( Classes.PopularBook.class, b.id );
		assertEquals( "Second", retrieved.editions.iterator().next().name );
		session.delete( retrieved );
		session.getTransaction().commit();
		session.close();
	}
