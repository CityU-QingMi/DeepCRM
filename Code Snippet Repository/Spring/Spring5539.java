	@Test
	public void testCountOccurrencesOf() {
		assertTrue("nullx2 = 0",
				StringUtils.countOccurrencesOf(null, null) == 0);
		assertTrue("null string = 0",
				StringUtils.countOccurrencesOf("s", null) == 0);
		assertTrue("null substring = 0",
				StringUtils.countOccurrencesOf(null, "s") == 0);
		String s = "erowoiueoiur";
		assertTrue("not found = 0",
				StringUtils.countOccurrencesOf(s, "WERWER") == 0);
		assertTrue("not found char = 0",
				StringUtils.countOccurrencesOf(s, "x") == 0);
		assertTrue("not found ws = 0",
				StringUtils.countOccurrencesOf(s, " ") == 0);
		assertTrue("not found empty string = 0",
				StringUtils.countOccurrencesOf(s, "") == 0);
		assertTrue("found char=2", StringUtils.countOccurrencesOf(s, "e") == 2);
		assertTrue("found substring=2",
				StringUtils.countOccurrencesOf(s, "oi") == 2);
		assertTrue("found substring=2",
				StringUtils.countOccurrencesOf(s, "oiu") == 2);
		assertTrue("found substring=3",
				StringUtils.countOccurrencesOf(s, "oiur") == 1);
		assertTrue("test last", StringUtils.countOccurrencesOf(s, "r") == 2);
	}
