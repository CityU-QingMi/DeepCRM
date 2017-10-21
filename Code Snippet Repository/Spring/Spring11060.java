	@SafeVarargs
	private final void assertPathElements(PathPattern p, Class<? extends PathElement>... sectionClasses) {
		PathElement head = p.getHeadSection();
		for (Class<? extends PathElement> sectionClass : sectionClasses) {
			if (head == null) {
				fail("Ran out of data in parsed pattern. Pattern is: " + p.toChainString());
			}
			assertEquals("Not expected section type. Pattern is: " + p.toChainString(),
					sectionClass.getSimpleName(), head.getClass().getSimpleName());
			head = head.next;
		}
	}
