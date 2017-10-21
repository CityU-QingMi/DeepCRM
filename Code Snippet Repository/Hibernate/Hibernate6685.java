	@Test
	public void testMapKeyEntityEntity() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		AddressBook book = new AddressBook();
		s.persist( book );
		AddressEntry entry = new AddressEntry();
		entry.setCity( "Atlanta");
		AddressEntryPk pk = new AddressEntryPk("Coca", "Cola" );
		entry.setPerson( pk );
		entry.setBook( book );
		AlphabeticalDirectory ad = new AlphabeticalDirectory();
		ad.setName( "C");
		s.persist( ad );
		entry.setDirectory( ad );
		s.persist( entry );
		book.getDirectoryEntries().put( ad, entry );

		s.flush();
		s.clear();

		book = (AddressBook) s.get( AddressBook.class, book.getId() );
		assertEquals( 1, book.getDirectoryEntries().size() );
		assertEquals( "C", book.getDirectoryEntries().keySet().iterator().next().getName() );
		tx.rollback();
		s.close();
	}
