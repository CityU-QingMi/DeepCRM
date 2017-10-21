	@Test
	public void compareNumberOfMatchingPatterns() throws Exception {
		Message<?> message = messageTo("/foo");

		DestinationPatternsMessageCondition c1 = condition("/foo", "bar");
		DestinationPatternsMessageCondition c2 = condition("/foo", "f*");

		DestinationPatternsMessageCondition match1 = c1.getMatchingCondition(message);
		DestinationPatternsMessageCondition match2 = c2.getMatchingCondition(message);

		assertEquals(1, match1.compareTo(match2, message));
	}
