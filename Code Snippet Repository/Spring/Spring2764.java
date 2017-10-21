	@Test
	public void testReturnTypeLowerBoundMatching() {
		testBean.getTestBeans();
		assertEquals(1, counterAspect.getTestBeanInvocationsCount);

		counterAspect.reset();

		testBean.getEmployees();
		assertEquals(0, counterAspect.getTestBeanInvocationsCount);
	}
