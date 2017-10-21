	@Test
	public void testCallVarargsFunction() {
		evaluate("#varargsFunctionReverseStringsAndMerge('a','b','c')", "cba", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge('a')", "a", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge()", "", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge('b',25)", "25b", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge(25)", "25", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge2(1,'a','b','c')", "1cba", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge2(2,'a')", "2a", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge2(3)", "3", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge2(4,'b',25)", "425b", String.class);
		evaluate("#varargsFunctionReverseStringsAndMerge2(5,25)", "525", String.class);
	}
