	@Test
	public void testReturnTypeExactMatching() {
		testBean.getStrings();
		assertEquals(1, counterAspect.getStringsInvocationsCount);
		assertEquals(0, counterAspect.getIntegersInvocationsCount);

		counterAspect.reset();

		testBean.getIntegers();
		assertEquals(0, counterAspect.getStringsInvocationsCount);
		assertEquals(1, counterAspect.getIntegersInvocationsCount);
	}
