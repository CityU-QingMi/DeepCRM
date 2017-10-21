	private void assertAddress(PersonBase person, String address) {
		List<Object> results = find(
				person.getClass(),
				person.id,
				"addresses"
		);
		assertEquals( 1, results.size() );

		assertEquals(
				person.addresses.get( 0 ).id,
				( (Address) results.get( 0 ) ).id
		);
		assertEquals( address, ( (Address) results.get( 0 ) ).name );

		getOrCreateEntityManager().close();
	}
