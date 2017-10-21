	@Test
	public void testMapKeyToEntity() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		AlphabeticalDirectory m = new AlphabeticalDirectory();
		m.setName( "M" );
		AlphabeticalDirectory v = new AlphabeticalDirectory();
		v.setName( "V" );
		s.persist( m );
		s.persist( v );

		AddressBook book = new AddressBook();
		book.setOwner( "Emmanuel" );
		AddressEntryPk helene = new AddressEntryPk( "Helene", "Michau" );
		AddressEntry heleneEntry = new AddressEntry();
		heleneEntry.setBook( book );
		heleneEntry.setCity( "Levallois" );
		heleneEntry.setStreet( "Louis Blanc" );
		heleneEntry.setPerson( helene );
		heleneEntry.setDirectory( m );
		AddressEntryPk primeMinister = new AddressEntryPk( "Dominique", "Villepin" );
		AddressEntry primeMinisterEntry = new AddressEntry();
		primeMinisterEntry.setBook( book );
		primeMinisterEntry.setCity( "Paris" );
		primeMinisterEntry.setStreet( "Hotel Matignon" );
		primeMinisterEntry.setPerson( primeMinister );
		primeMinisterEntry.setDirectory( v );
		book.getEntries().put( helene, heleneEntry );
		book.getEntries().put( primeMinister, primeMinisterEntry );
		s.persist( book );

		s.flush();
		s.clear();

		book = (AddressBook) s.get( AddressBook.class, book.getId() );
		assertEquals( 2, book.getEntries().size() );
		assertEquals( heleneEntry.getCity(), book.getEntries().get( helene ).getCity() );
		assertEquals( "M", book.getEntries().get( helene ).getDirectory().getName() );

		s.delete( book );
		tx.rollback();
		s.close();
	}
