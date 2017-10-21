	@Test
	public void straightLineDependency() throws Exception {
		runTest(new StraightLineDependencyController());
		assertInvokedBefore("getA", "getB1", "getB2", "getC1", "getC2", "getC3", "getC4");
		assertInvokedBefore("getB1", "getB2", "getC1", "getC2", "getC3", "getC4");
		assertInvokedBefore("getB2", "getC1", "getC2", "getC3", "getC4");
		assertInvokedBefore("getC1", "getC2", "getC3", "getC4");
		assertInvokedBefore("getC2", "getC3", "getC4");
		assertInvokedBefore("getC3", "getC4");
	}
