	@Test
	public void testUniqueCounter() {
		CustomVersionOneStrategy strategy = new CustomVersionOneStrategy();
		long now = System.currentTimeMillis();
		UUID uuid1 = new UUID(
				strategy.getMostSignificantBits(),
				CustomVersionOneStrategy.generateLeastSignificantBits( now )
		);
		assertEquals( 2, uuid1.variant() );
		assertEquals( 1, uuid1.version() );

		for ( int i = 0; i < 100; i++ ) {
			UUID uuidX = new UUID(
					strategy.getMostSignificantBits(),
					CustomVersionOneStrategy.generateLeastSignificantBits( now )
			);
			assertEquals( 2, uuidX.variant() );
			assertEquals( 1, uuidX.version() );
			assertFalse( uuid1.equals( uuidX ) );
			assertEquals( uuid1.getMostSignificantBits(), uuidX.getMostSignificantBits() );
		}
	}
