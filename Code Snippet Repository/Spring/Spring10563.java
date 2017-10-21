	@Test
	public void destructionAtRequestCompletion() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		String name = "requestScopedDisposableObject";
		assertNull(request.getAttribute(name));
		DerivedTestBean bean = (DerivedTestBean) this.beanFactory.getBean(name);
		assertSame(bean, request.getAttribute(name));
		assertSame(bean, this.beanFactory.getBean(name));

		requestAttributes.requestCompleted();
		assertTrue(bean.wasDestroyed());
	}
