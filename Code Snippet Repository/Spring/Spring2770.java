	@Test
	public void testIsProxy() throws Exception {
		ITestBean bean = getTestBean();

		assertTrue("Bean is not a proxy", AopUtils.isAopProxy(bean));

		// check the advice details
		Advised advised = (Advised) bean;
		Advisor[] advisors = advised.getAdvisors();

		assertTrue("Advisors should not be empty", advisors.length > 0);
	}
