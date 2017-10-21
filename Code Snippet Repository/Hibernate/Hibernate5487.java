	@Test
	public void testRangeOfValues() {
		CustomVersionOneStrategy strategy = new CustomVersionOneStrategy();

		UUID uuid = new UUID(
				strategy.getMostSignificantBits(),
				CustomVersionOneStrategy.generateLeastSignificantBits( 0 )
		);
		assertEquals( 2, uuid.variant() );
		assertEquals( 1, uuid.version() );

		uuid = new UUID(
				strategy.getMostSignificantBits(),
				CustomVersionOneStrategy.generateLeastSignificantBits( Long.MAX_VALUE )
		);
		assertEquals( 2, uuid.variant() );
		assertEquals( 1, uuid.version() );
	}
