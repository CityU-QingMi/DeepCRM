	@Test
	public void destructionAtSessionTermination() throws Exception {
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);

		RequestContextHolder.setRequestAttributes(requestAttributes);
		String name = "sessionScopedDisposableObject";
		assertNull(session.getAttribute(name));
		DerivedTestBean bean = (DerivedTestBean) this.beanFactory.getBean(name);
		assertEquals(session.getAttribute(name), bean);
		assertSame(bean, this.beanFactory.getBean(name));

		requestAttributes.requestCompleted();
		session.invalidate();
		assertTrue(bean.wasDestroyed());
	}
