	private void assertAddress(PersonBaseBase person, String address, String localAddress) {
		List<Object> results = find( person.getClass(), person.id, "addresses" );
		assertEquals( 1, results.size() );

		assertEquals( person.addresses.get( 0 ).id, ( (Address) results.get( 0 ) ).id );
		assertEquals( address, ( (Address) results.get( 0 ) ).name );


		results = find( person.getClass(), person.id, "localAddresses" );
		assertEquals( 1, results.size() );

		assertEquals( person.getLocalAddresses().get( 0 ).id, ( (Address) results.get( 0 ) ).id );
		assertEquals( localAddress, ( (Address) results.get( 0 ) ).name );

		getOrCreateEntityManager().close();
	}
