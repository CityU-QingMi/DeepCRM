	@Test
	public void sessionScope() throws Exception {
		final String beanName = "sessionScopedTestBean";

		assertNull(session.getAttribute(beanName));

		TestBean testBean = wac.getBean(beanName, TestBean.class);

		assertSame(testBean, session.getAttribute(beanName));
		assertSame(testBean, wac.getBean(beanName, TestBean.class));
	}
