	@Test
	public void testAspectApplied() throws Exception {
		ITestBean bean = getTestBean();

		CountingAspectJAdvice advice = (CountingAspectJAdvice) this.context.getBean("countingAdvice");

		assertEquals("Incorrect before count", 0, advice.getBeforeCount());
		assertEquals("Incorrect after count", 0, advice.getAfterCount());

		bean.setName("Sally");

		assertEquals("Incorrect before count", 1, advice.getBeforeCount());
		assertEquals("Incorrect after count", 1, advice.getAfterCount());

		bean.getName();

		assertEquals("Incorrect before count", 1, advice.getBeforeCount());
		assertEquals("Incorrect after count", 1, advice.getAfterCount());
	}
