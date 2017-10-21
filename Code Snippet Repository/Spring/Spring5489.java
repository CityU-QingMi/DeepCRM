	@Test
	public void testStartsEndsBetween() {
		doTest("12*45*78", "12345678", true);
		doTest("12*45*78", "123456789", false);
		doTest("12*45*78", "012345678", false);
		doTest("12*45*78", "124578", true);
		doTest("12*45*78", "1245457878", true);
		doTest("3*3*3", "33", false);
		doTest("3*3*3", "333", true);
	}
