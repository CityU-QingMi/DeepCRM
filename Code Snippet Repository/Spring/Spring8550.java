	@Test
	public void requestScope() throws Exception {
		final String beanName = "requestScopedTestBean";
		final String contextPath = "/path";

		assertNull(request.getAttribute(beanName));

		request.setContextPath(contextPath);
		TestBean testBean = wac.getBean(beanName, TestBean.class);

		assertEquals(contextPath, testBean.getName());
		assertSame(testBean, request.getAttribute(beanName));
		assertSame(testBean, wac.getBean(beanName, TestBean.class));
	}
