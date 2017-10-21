	@Test
	public void testReturnTypeRawMatching() {
		testBean.getStrings();
		assertEquals(1, counterAspect.getRawsInvocationsCount);

		counterAspect.reset();

		testBean.getIntegers();
		assertEquals(1, counterAspect.getRawsInvocationsCount);
	}
