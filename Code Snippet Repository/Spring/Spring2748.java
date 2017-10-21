	protected void doTestAspectsAndAdvisorAreApplied(ApplicationContext ac, ITestBean shouldBeWeaved) {
		TestBeanAdvisor tba = (TestBeanAdvisor) ac.getBean("advisor");

		MultiplyReturnValue mrv = (MultiplyReturnValue) ac.getBean("aspect");
		assertEquals(3, mrv.getMultiple());

		tba.count = 0;
		mrv.invocations = 0;

		assertTrue("Autoproxying must apply from @AspectJ aspect", AopUtils.isAopProxy(shouldBeWeaved));
		assertEquals("Adrian", shouldBeWeaved.getName());
		assertEquals(0, mrv.invocations);
		assertEquals(34 * mrv.getMultiple(), shouldBeWeaved.getAge());
		assertEquals("Spring advisor must be invoked", 2, tba.count);
		assertEquals("Must be able to hold state in aspect", 1, mrv.invocations);
	}
