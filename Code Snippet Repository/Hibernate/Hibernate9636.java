	@Test
	public void testNullIterables() {
		try {
			new JoinedIterable<String>( null );
			fail();
		}
		catch (NullPointerException ex) {
			// expected
		}
	}
