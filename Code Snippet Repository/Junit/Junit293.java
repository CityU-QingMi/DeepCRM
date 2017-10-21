	@Test
	void assertLinesMatchMatches() {
		Random random = new Random();
		assertAll("do match", //
			() -> assertTrue(
				AssertLinesMatch.matches("duration: [\\d]+ ms", "duration: " + random.nextInt(1000) + " ms")),
			() -> assertTrue(AssertLinesMatch.matches("123", "123")),
			() -> assertTrue(AssertLinesMatch.matches(".*", "123")),
			() -> assertTrue(AssertLinesMatch.matches("\\d+", "123")));
		assertAll("don't match", //
			() -> assertFalse(
				AssertLinesMatch.matches("duration: [\\d]+ ms", "duration: " + random.nextGaussian() + " ms")),
			() -> assertFalse(AssertLinesMatch.matches("12", "123")),
			() -> assertFalse(AssertLinesMatch.matches("..+", "1")),
			() -> assertFalse(AssertLinesMatch.matches("\\d\\d+", "1")));
	}
