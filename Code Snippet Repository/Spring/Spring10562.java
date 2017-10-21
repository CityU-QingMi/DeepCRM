	@Test
	public void getFromScope() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("/path");
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		String name = "requestScopedObject";
		assertNull(request.getAttribute(name));
		TestBean bean = (TestBean) this.beanFactory.getBean(name);
		assertEquals("/path", bean.getName());
		assertSame(bean, request.getAttribute(name));
		assertSame(bean, this.beanFactory.getBean(name));
	}
