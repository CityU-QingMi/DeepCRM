	@Test
	public void getFromScopeWithSingleAccess() throws Exception {
		AtomicInteger count = new AtomicInteger();
		MockHttpSession session = new MockHttpSession() {
			@Override
			public void setAttribute(String name, Object value) {
				super.setAttribute(name, value);
				count.incrementAndGet();
			}
		};
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);

		RequestContextHolder.setRequestAttributes(requestAttributes);
		String name = "sessionScopedObject";
		assertNull(session.getAttribute(name));
		TestBean bean = (TestBean) this.beanFactory.getBean(name);
		assertEquals(1, count.intValue());

		// should re-propagate updated attribute
		requestAttributes.requestCompleted();
		assertEquals(session.getAttribute(name), bean);
		assertEquals(2, count.intValue());
	}
