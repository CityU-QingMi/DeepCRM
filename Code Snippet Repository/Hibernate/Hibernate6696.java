	@Test
	@RequiresDialect({HSQLDialect.class, H2Dialect.class})
	public void testComponentSubPropertyMapKey() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		AddressBook book = new AddressBook();
		book.setOwner( "Emmanuel" );
		AddressEntryPk helene = new AddressEntryPk( "Helene", "Michau" );
		AddressEntry heleneEntry = new AddressEntry();
		heleneEntry.setBook( book );
		heleneEntry.setCity( "Levallois" );
		heleneEntry.setStreet( "Louis Blanc" );
		heleneEntry.setPerson( helene );
		AddressEntryPk primeMinister = new AddressEntryPk( "Dominique", "Villepin" );
		AddressEntry primeMinisterEntry = new AddressEntry();
		primeMinisterEntry.setBook( book );
		primeMinisterEntry.setCity( "Paris" );
		primeMinisterEntry.setStreet( "Hotel Matignon" );
		primeMinisterEntry.setPerson( primeMinister );
		book.getEntries().put( helene, heleneEntry );
		book.getEntries().put( primeMinister, primeMinisterEntry );
		s.persist( book );

		s.flush();
		s.clear();

		book = (AddressBook) s.get( AddressBook.class, book.getId() );
		assertEquals( 2, book.getLastNameEntries().size() );
		assertEquals( heleneEntry.getCity(), book.getLastNameEntries().get( "Michau" ).getCity() );
		AddressEntryPk fake = new AddressEntryPk( "Fake", "Fake" );
		book.getEntries().put( fake, primeMinisterEntry );

		s.flush();
		s.clear();

		book = (AddressBook) s.get( AddressBook.class, book.getId() );
		assertEquals( 2, book.getEntries().size() );
		assertNull( book.getEntries().get( fake ) );
		s.delete( book );
		tx.rollback();
		s.close();
	}
