	@Test
	public void testSingleUser() throws Exception {
		// setup
		sessionFactory().getStatistics().clear();
		// wait a while to make sure that timestamp comparison works after invalidateRegion
		TIME_SERVICE.advance(1);

		Customer customer = createCustomer( 0 );
		final Integer customerId = customer.getId();
		getCustomerIDs().add( customerId );

		// wait a while to make sure that timestamp comparison works after collection remove (during insert)
		TIME_SERVICE.advance(1);

		assertNull( "contact exists despite not being added", getFirstContact( customerId ) );

		// check that cache was hit
		SecondLevelCacheStatistics customerSlcs = sessionFactory()
				.getStatistics()
				.getSecondLevelCacheStatistics( Customer.class.getName() );
		assertEquals( 1, customerSlcs.getPutCount() );
		assertEquals( 1, customerSlcs.getElementCountInMemory() );
		assertEquals( 1, customerSlcs.getEntries().size() );

		log.infof( "Add contact to customer {0}", customerId );
		SecondLevelCacheStatistics contactsCollectionSlcs = sessionFactory()
				.getStatistics()
				.getSecondLevelCacheStatistics( Customer.class.getName() + ".contacts" );
		assertEquals( 1, contactsCollectionSlcs.getPutCount() );
		assertEquals( 1, contactsCollectionSlcs.getElementCountInMemory() );
		assertEquals( 1, contactsCollectionSlcs.getEntries().size() );

		final Contact contact = addContact( customerId );
		assertNotNull( "contact returned by addContact is null", contact );
		assertEquals(
				"Customer.contacts cache was not invalidated after addContact", 0,
				contactsCollectionSlcs.getElementCountInMemory()
		);

		assertNotNull( "Contact missing after successful add call", getFirstContact( customerId ) );

		// read everyone's contacts
		readEveryonesFirstContact();

		removeContact( customerId );
		assertNull( "contact still exists after successful remove call", getFirstContact( customerId ) );

	}
