	@Test
	public void InvertedTreeDependency() throws Exception {
		runTest(new InvertedTreeDependencyController());
		assertInvokedBefore("getC1", "getA", "getB1");
		assertInvokedBefore("getC2", "getA", "getB1");
		assertInvokedBefore("getC3", "getA", "getB2");
		assertInvokedBefore("getC4", "getA", "getB2");
		assertInvokedBefore("getB1", "getA");
		assertInvokedBefore("getB2", "getA");
	}
